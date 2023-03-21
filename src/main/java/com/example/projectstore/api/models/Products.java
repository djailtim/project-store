package com.example.projectstore.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
public class Products {
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
}
