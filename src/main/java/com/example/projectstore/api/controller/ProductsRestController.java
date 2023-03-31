package com.example.projectstore.api.controller;

import com.example.projectstore.api.responses.ProductResponse;
import com.example.projectstore.api.services.ProductsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsRestController {
    private final ProductsService productsService;

    public ProductsRestController(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping(headers = "token")
    public List<ProductResponse> getAll(@RequestHeader("token") String token){
        return productsService.getAll(token);
    }

    @GetMapping(value = "/category/{categoryName}", headers = "token")
    public List<ProductResponse> findByCategory(@PathVariable("categoryName") String categoryName, @RequestHeader String token){
       return productsService.findByCategory(categoryName, token);
    }
    @GetMapping(value = "/{id}", headers = "token")
    public ProductResponse getId(@PathVariable("id") Long productDTOId, @RequestHeader String token){
        return productsService.getById(productDTOId, token);
    }

    @GetMapping(value = "/searchbyname", params = "q", headers = "token")
    public List<ProductResponse> searchByName(@RequestParam("q") String name, @RequestHeader String token) {
        return productsService.searchByName(name, token);
    }
}
