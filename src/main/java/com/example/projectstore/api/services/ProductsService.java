package com.example.projectstore.api.services;

import com.example.projectstore.clients.dummy.ProductsDTO;
import com.example.projectstore.clients.dummy.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    public List<ProductsDTO> getAll() {
        return productsRepository.getAll().getProductsListDTO();
    }

    public List<ProductsDTO> findByCategory(String categoryName) {
        return productsRepository.findByCategory(categoryName).getProductsListDTO();
    }

    public List<ProductsDTO> getById(Long productDTOId) {
        return new ArrayList<>(List.of(productsRepository.getById(productDTOId)));
    }

    public List<ProductsDTO> search(String name) { //OPTIONAL???
        return productsRepository.search(name).getProductsListDTO().stream().filter(products ->
                products.getTitle().toLowerCase().contains(name.toLowerCase())).toList();
    }
}
