package com.example.projectstore.clients.dummyJsonAPI;

import com.example.projectstore.clients.awesomeAPI.AwesomeAPIRestController;
import com.example.projectstore.clients.dummyJsonAPI.DTO.ProductDTO;
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

    public List<ProductDTO> getAll(String toConvertCountryCode) {
        List<ProductDTO> productDTOList = restRepository.getAll().getProductDTOS();
        return converter(productDTOList, toConvertCountryCode);
    }

    public List<ProductDTO> findById(Long id, String toConvertCountryCode) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(restRepository.findById(id));
        return converter(productDTOList, toConvertCountryCode);
    }

    public List<ProductDTO> search(String product, String toConvertCountryCode) {
        List<ProductDTO> productDTOList = restRepository.search(product).getProductDTOS();
        return converter(productDTOList, toConvertCountryCode);
    }

    public List<String> categories() {
        return restRepository.categories();
    }

    public List<ProductDTO> findByCategory(String categoryName, String toConvertCountryCode) {
        List<ProductDTO> productDTOList = restRepository.findByCategory(categoryName).getProductDTOS();
        return converter(productDTOList, toConvertCountryCode);
    }

    public List<ProductDTO> converter(List<ProductDTO> productDTOList, String toConvertCountryCode) {
        if (!toConvertCountryCode.equals("USD")) {
            BigDecimal coefficient = apiRestController.search("USD".concat("-".concat(toConvertCountryCode))).getCoefficient();
            return productDTOList.stream().peek(products -> products.converter(coefficient)).toList();
        } else return productDTOList;
    }
}


