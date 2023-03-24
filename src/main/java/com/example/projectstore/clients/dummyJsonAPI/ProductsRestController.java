package com.example.projectstore.clients.dummyJsonAPI;
import com.example.projectstore.clients.dummyJsonAPI.DTO.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductsRestController {

    private final ProductsAPIService service;

    public ProductsRestController(ProductsAPIService service) {
        this.service = service;
    }

    @GetMapping(headers = "toConvertCountryCode")
    public List<ProductDTO> getAll(@RequestHeader String toConvertCountryCode){
        return service.getAll(toConvertCountryCode);
    }
    @GetMapping(value = "/{id}", headers = "toConvertCountryCode")
    public List<ProductDTO> findById(@PathVariable Long id, @RequestHeader String toConvertCountryCode){
        return service.findById(id, toConvertCountryCode);
    }

    @GetMapping(value = "/search", headers = "toConvertCountryCode")
    public List<ProductDTO> search(@RequestParam("q") String product, @RequestHeader String toConvertCountryCode){
        return service.search(product, toConvertCountryCode);
    }
    @GetMapping("/categories")
    public List<String> findCategories(){
       return service.categories();
    }

    @GetMapping(value = "/category/{categoryName}", headers = "toConvertCountryCode")
    public List<ProductDTO> findyByCategory(@PathVariable("categoryName") String categoryName, @RequestHeader String toConvertCountryCode){
        return service.findByCategory(categoryName, toConvertCountryCode);
    }
}
