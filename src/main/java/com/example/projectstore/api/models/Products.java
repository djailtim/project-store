package com.example.projectstore.api.models;

import com.example.projectstore.api.util.Converter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Products implements Converter {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("category")
    private String category;
    @JsonProperty("images")
    private List<String> imgList;

    @Override
    public void converter( BigDecimal coefficient) {
    setPrice(this.price.multiply(coefficient));
    }
}
