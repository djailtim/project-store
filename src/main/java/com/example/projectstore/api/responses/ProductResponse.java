package com.example.projectstore.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponse {


    private String title;

    private String description;

    private String price;

    private String brand;

    private String category;

    private String thumbnail;

}
