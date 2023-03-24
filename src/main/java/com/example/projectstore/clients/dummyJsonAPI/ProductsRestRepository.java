package com.example.projectstore.clients.dummyJsonAPI;


import com.example.projectstore.clients.dummyJsonAPI.DTO.ProductDTO;
import com.example.projectstore.clients.dummyJsonAPI.DTO.ProductsListDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@FeignClient(name = "ProductsRestRepository", url = "${dummyjsonproductsapi.url}")
public interface ProductsRestRepository {
    @GetMapping
    ProductsListDTO getAll();

    @GetMapping("/{id}")
    ProductDTO findById(@PathVariable Long id);

    @GetMapping(value = "/search", params = "q")
    ProductsListDTO search(@RequestParam("q") String product);

    @GetMapping("/categories")
    List<String> categories();

    @GetMapping("/category/{categoryName}")
    ProductsListDTO findByCategory(@PathVariable("categoryName") String categoryName);
}

