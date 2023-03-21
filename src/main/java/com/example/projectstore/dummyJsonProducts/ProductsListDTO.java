package com.example.projectstore.dummyJsonProducts;

import com.example.projectstore.api.models.Products;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
public class ProductsListDTO {
    @JsonProperty("products")
    private List<Products> products;
}
