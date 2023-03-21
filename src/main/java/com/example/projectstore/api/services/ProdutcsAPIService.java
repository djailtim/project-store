package com.example.projectstore.api.services;

import com.example.projectstore.api.models.Products;
import com.example.projectstore.api.repositories.ProductsRestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutcsAPIService {
    private final ProductsRestRepository restRepository;

    public ProdutcsAPIService(ProductsRestRepository restRepository) {
        this.restRepository = restRepository;
    }
    public List<Products> getAll(){
        return restRepository.getAll().getProducts();
    }

    public Products findById(Long id) {
        return restRepository.findById(id);
    }

    public List<Products> search(String product) {
        return restRepository.search(product).getProducts();
    }

    public List<String> categories() {
        return restRepository.categories();
    }

    public List<Products> findByCategory(String categoryName) {
        return restRepository.findByCategory(categoryName).getProducts();
    }
}
