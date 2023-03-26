package com.example.projectstore.api.controller;

import com.example.projectstore.api.response.ProductResponse;
import com.example.projectstore.api.services.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ProductResponse> getId(@PathVariable("id") Long productDTOId){
        return productsService.getById(productDTOId);
    }

    @GetMapping(value = "/searchbyname", params = "q")
    public List<ProductResponse> searchByName(@RequestParam("q") String name) {
        return productsService.search(name);
    }
}
