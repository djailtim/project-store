package com.example.projectstore.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponse {

    private Long productDTOId;

    private String title;

    private String description;

    private BigDecimal price;

    private String brand;

    private String category;

    private String thumbnail;

}
