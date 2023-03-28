package com.example.projectstore.api.controller;

import com.example.projectstore.api.responses.ProductResponse;
import com.example.projectstore.api.services.ProductsService;
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

    @GetMapping
    public List<ProductResponse> getAll(){
        return productsService.getAll();
    }

    @GetMapping(value = "/category/{categoryName}")
    public List<ProductResponse> findByCategory(@PathVariable("categoryName") String categoryName){
       return productsService.findByCategory(categoryName);
    }
    @GetMapping("/{id}")
    public Optional<ProductResponse> getId(@PathVariable("id") Long productDTOId){
        return productsService.getById(productDTOId);
    }

    @GetMapping(value = "/searchbyname", params = "q")
    public List<ProductResponse> searchByName(@RequestParam("q") String name) {
        return productsService.searchByName(name);
    }
}
