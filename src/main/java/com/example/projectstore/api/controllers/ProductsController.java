package com.example.projectstore.api.controllers;
import com.example.projectstore.api.services.ProdutcsAPIService;
import com.example.projectstore.api.models.Products;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProdutcsAPIService service;

    public ProductsController(ProdutcsAPIService service) {
        this.service = service;
    }

    @GetMapping
    public List<Products> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Products findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping(value = "/search", params = "q")
    public List<Products> search(@RequestParam("q") String product){
        return service.search(product);
    }
    @GetMapping("/categories")
    public List<String> findCategories(){
       return service.categories();
    }

    @GetMapping("/category/{categoryName}")
    public List<Products> findyByCategory(@PathVariable("categoryName") String categoryName){
        return service.findByCategory(categoryName);
    }
}
