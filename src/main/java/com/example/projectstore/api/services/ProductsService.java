package com.example.projectstore.api.services;
import com.example.projectstore.api.exceptions.CategoryNotFoundException;
import com.example.projectstore.api.model.Product;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.ProductsDBRepository;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.responses.ProductResponse;
import com.example.projectstore.api.system.JwtService;
import com.example.projectstore.clients.dummy.ProductDTO;
import com.example.projectstore.clients.dummy.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsDBRepository DBRepository;
    private final ModelMapper modelMapper;

    private final JwtService jwtService;

    private final UserRepository userRepository;
    private final AwesomeService awesomeService;

    public ProductsService(ProductsRepository productsRepository, ProductsDBRepository DBRepository, JwtService jwtService, UserRepository userRepository, AwesomeService awesomeService) {
        this.DBRepository = DBRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.awesomeService = awesomeService;
        this.modelMapper = new ModelMapper();
        checkToSave(productsRepository.getAll(100L).getProductsListDTO());
    }

    public List<ProductResponse> getAll(String token){
       return DBRepository.findAll().stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class))
               .peek(productResponse -> convertPrice(productResponse, token)).toList();
    }

    public List<ProductResponse> findByCategory(String categoryName, String token) {
        List<ProductResponse> products = DBRepository.findByCategory(categoryName).stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .peek(productResponse -> convertPrice(productResponse, token)).toList();
        if (products.isEmpty()) throw new CategoryNotFoundException("Categoria não encontrada.");
        return products;
    }

    private BigDecimal getCoefficient(String token) {
        String email =  jwtService.extractUsername(token);
        Optional<User> user = userRepository.findByEmail(email);
        BigDecimal coefficient = BigDecimal.ONE;
        if (user.isPresent()){
            if(!user.get().getCurrency().equals("USD")) coefficient = awesomeService.search("USD-" + user.get().getCurrency()).getCoefficient();
        }
        return coefficient;
    }

    public ProductResponse getById(Long productDTOId, String token) {
        Optional<ProductResponse> optional = new ArrayList<>(List.of(DBRepository.findProductByProductDTOid(productDTOId))).stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).findAny();
      optional.ifPresent(productResponse -> convertPrice(productResponse, token));
      return optional.orElseThrow();// ESTOURAR EXCESSAO;
    }

    private ProductResponse convertPrice(ProductResponse productResponse, String token){
        BigDecimal coefficient = getCoefficient(token);
        productResponse.setPrice(productResponse.getPrice().multiply(coefficient).setScale(2, RoundingMode.UP));
       return productResponse;
    }

    public List<ProductResponse> searchByName(String name, String token) { //OPTIONAL???
        return DBRepository.searchByTitleStartingWith(name).stream().map( product ->
                modelMapper.map(product, ProductResponse.class))
                .peek(productResponse -> convertPrice(productResponse, token)).toList();
    }
    public void checkToSave(List<ProductDTO> listDTO) {
        listDTO.stream().map(productDTO -> modelMapper.map(productDTO, Product.class))
                .filter(productDTO -> !DBRepository.findAll().contains(productDTO))
                .forEach(DBRepository::save);
    }
}
