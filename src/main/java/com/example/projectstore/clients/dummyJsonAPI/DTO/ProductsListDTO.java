package com.example.projectstore.clients.dummyJsonAPI.DTO;

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
    private List<ProductDTO> productDTOS;
}
