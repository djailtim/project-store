package com.example.projectstore.clients.dummy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "productsRepository", url = "https://dummyjson.com/products/")
public interface ProductsRepository {
    @GetMapping
    ProductsListDTO getAll();
}