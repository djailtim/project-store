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

@Service
public class ProductsService {
    private final ProductDBRepository DBRepository;
    private final ModelMapper modelMapper;

    public ProductsService(ProductsRepository productsRepository, ProductDBRepository DBRepository) {
        this.DBRepository = DBRepository;
        this.modelMapper = new ModelMapper();
        checkToSave(productsRepository.getAll(100L).getProductsListDTO());
    }

    public List<ProductResponse> getAll(){

       return DBRepository.findAll().stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }

    public List<ProductResponse> findByCategory(String categoryName) {
        return DBRepository.findByCategory(categoryName).stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }

    public List<ProductResponse> getById(Long productDTOId) {
        return new ArrayList<>(List.of(DBRepository.findProductByProductDTOid(productDTOId))).stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }


    public List<ProductResponse> searchByName(String name) { //OPTIONAL???
        return DBRepository.searchByTitleStartingWith(name).stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }
    public void checkToSave(List<ProductsDTO> listDTO) {
        listDTO.stream().map(productDTO -> modelMapper.map(productDTO, Product.class))
                .filter(productDTO -> !DBRepository.findAll().contains(productDTO))
                .forEach(DBRepository::save);
    }
}
