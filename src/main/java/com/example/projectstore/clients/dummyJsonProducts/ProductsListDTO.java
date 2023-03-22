package com.example.projectstore.clients.dummyJsonProducts;

import com.example.projectstore.api.models.Products;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductsListDTO {
    @JsonProperty("products")
    private List<Products> products;
}
