package com.example.projectstore.clients.dummy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "productsRepository", url = "https://dummyjson.com/products/")
public interface ProductsRepository {
    @GetMapping(params = "limit")
    ProductsListDTO getAll(@RequestParam("limit") Long limit);

    @GetMapping("/category/{categoryName}")
    ProductsListDTO findByCategory(@PathVariable String categoryName);

    @GetMapping("/{id}")
    ProductDTO getById(@PathVariable("id") Long productDTOId);

    @GetMapping(value = "/search", params = "q")
    ProductsListDTO search(@RequestParam("q") String query);

}