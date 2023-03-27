package com.example.projectstore.api.services;
import com.example.projectstore.api.model.Product;
import com.example.projectstore.api.repositories.ProductDBRepository;
import com.example.projectstore.api.responses.ProductResponse;
import com.example.projectstore.clients.dummy.ProductsDTO;
import com.example.projectstore.clients.dummy.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductDBRepository DBRepository;
    private final ModelMapper modelMapper;

    public ProductsService(ProductsRepository productsRepository, ProductDBRepository DBRepository) {
        this.productsRepository = productsRepository;
        this.DBRepository = DBRepository;
        this.modelMapper = new ModelMapper();

    }

    public List<ProductResponse> getAll(){
        List<ProductsDTO> dtoList = productsRepository.getAll(100L).getProductsListDTO();
        checkToSave(dtoList);
       return productsRepository.getAll(100L).getProductsListDTO().stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }

    public List<ProductResponse> findByCategory(String categoryName) {
        List<ProductsDTO> dtoList = productsRepository.findByCategory(categoryName).getProductsListDTO();
        checkToSave(dtoList);
        return productsRepository.findByCategory(categoryName).getProductsListDTO().stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }

    public List<ProductResponse> getById(Long productDTOId) {
        List<ProductsDTO> dtoList = List.of(productsRepository.getById(productDTOId));
        checkToSave(dtoList);
        return new ArrayList<>(List.of(productsRepository.getById(productDTOId))).stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }


    public List<ProductResponse> search(String name) { //OPTIONAL???
        List<ProductsDTO> dtoList = productsRepository.search(name).getProductsListDTO();
        checkToSave(dtoList);
        return productsRepository.search(name).getProductsListDTO().stream().filter(products ->
                products.getTitle().toLowerCase().contains(name.toLowerCase())).map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }
    public void checkToSave(List<ProductsDTO> listDTO) {
        listDTO.stream().map(productDTO -> modelMapper.map(productDTO, Product.class))
                .filter(productDTO -> !DBRepository.findAll().contains(productDTO))
                .forEach(DBRepository::save);
    }
}
