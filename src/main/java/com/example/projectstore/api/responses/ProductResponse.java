package com.example.projectstore.api.responses;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponse {

    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private String brand;

    private String category;

    private String thumbnail;

}
