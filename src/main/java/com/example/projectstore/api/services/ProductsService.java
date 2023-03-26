package com.example.projectstore.api.services;
import com.example.projectstore.api.model.Product;
import com.example.projectstore.api.repositories.ProductDBRepository;
import com.example.projectstore.api.response.ProductResponse;
import com.example.projectstore.clients.dummy.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
        DBRepository.saveAll(productsRepository.getAll().getProductsListDTO().stream()
                .map(productsDTO -> modelMapper.map(productsDTO, Product.class)).toList());
       return productsRepository.getAll().getProductsListDTO().stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }

    public List<ProductResponse> findByCategory(String categoryName) {
        DBRepository.saveAll(productsRepository.findByCategory(categoryName).getProductsListDTO().stream()
                .map(productsDTO -> modelMapper.map(productsDTO, Product.class)).toList());
        return productsRepository.findByCategory(categoryName).getProductsListDTO().stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }

    public List<ProductResponse> getById(Long productDTOId) {
        DBRepository.save(modelMapper.map(productsRepository.getById(productDTOId), Product.class));
        return new ArrayList<>(List.of(productsRepository.getById(productDTOId))).stream().map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }


    public List<ProductResponse> search(String name) { //OPTIONAL???
        DBRepository.saveAll(productsRepository.search(name).getProductsListDTO().stream()
                .map(productsDTO -> modelMapper.map(productsDTO, Product.class)).toList());
        return productsRepository.search(name).getProductsListDTO().stream().filter(products ->
                products.getTitle().toLowerCase().contains(name.toLowerCase())).map( productsDTO ->
                modelMapper.map(productsDTO, ProductResponse.class)).toList();
    }
}
