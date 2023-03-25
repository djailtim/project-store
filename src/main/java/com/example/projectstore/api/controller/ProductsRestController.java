package com.example.projectstore.api.controller;

import com.example.projectstore.clients.dummy.ProductsListDTO;
import com.example.projectstore.clients.dummy.ProductsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsRestController {
    private final ProductsRepository productsRepository;

    public ProductsRestController(ProductsRepository productsRepository ){
        this.productsRepository = productsRepository;
    }

    @GetMapping
    public ProductsListDTO getAll(){
        return productsRepository.getAll();
    }
}
