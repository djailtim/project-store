package com.example.projectstore.api.controller;

import com.example.projectstore.api.responses.ProductResponse;
import com.example.projectstore.api.services.ProductsService;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping
    public List<ProductResponse> getAll(HttpServletRequest request){
        return productsService.getAll(request);
    }

    @GetMapping(value = "/category/{categoryName}")
    public List<ProductResponse> findByCategory(@PathVariable("categoryName") String categoryName,HttpServletRequest request){
       return productsService.findByCategory(categoryName, request);
    }
    @GetMapping(value = "/{id}")
    public ProductResponse getId(@PathVariable("id") Long productDTOId,HttpServletRequest request){
        return productsService.getById(productDTOId, request);
    }

    @GetMapping(value = "/searchbyname", params = "q")
    public List<ProductResponse> searchByName(@RequestParam("q") String name, HttpServletRequest request) {
        return productsService.searchByName(name, request);
    }
}
