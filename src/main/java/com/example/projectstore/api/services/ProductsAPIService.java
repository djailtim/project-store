package com.example.projectstore.api.services;

import com.example.projectstore.api.controllers.AwesomeAPIRestController;
import com.example.projectstore.api.models.Products;
import com.example.projectstore.api.repositories.ProductsRestRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsAPIService {
    private final ProductsRestRepository restRepository;
    private final AwesomeAPIRestController apiRestController;

    public ProductsAPIService(ProductsRestRepository restRepository, AwesomeAPIRestController apiRestController) {
        this.restRepository = restRepository;
        this.apiRestController = apiRestController;
    }

    public List<Products> getAll(String toConvertCountryCode) {
        List<Products> productsList = restRepository.getAll().getProducts();
        return converter(productsList, toConvertCountryCode);
    }

    public List<Products> findById(Long id, String toConvertCountryCode) {
        List<Products> productsList = new ArrayList<>();
        productsList.add(restRepository.findById(id));
        return converter(productsList, toConvertCountryCode);
    }

    public List<Products> search(String product, String toConvertCountryCode) {
        List<Products> productsList = restRepository.search(product).getProducts();
        return converter(productsList, toConvertCountryCode);
    }

    public List<String> categories() {
        return restRepository.categories();
    }

    public List<Products> findByCategory(String categoryName, String toConvertCountryCode) {
        List<Products> productsList = restRepository.findByCategory(categoryName).getProducts();
        return converter(productsList, toConvertCountryCode);
    }

    public List<Products> converter(List<Products> productsList, String toConvertCountryCode) {
        if (!toConvertCountryCode.equals("USD")) {
            BigDecimal coefficient = apiRestController.search("USD".concat("-".concat(toConvertCountryCode))).getCoefficient();
            return productsList.stream().peek(products -> products.converter(coefficient)).toList();
        } else return productsList;
    }
}


