package com.example.projectstore.clients.dummyJsonAPI.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {

    @JsonProperty("id")
    private Long productId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("category")
    private String category;
    @JsonProperty("thumbnail")
    private String imgUrl;

    public void converter( BigDecimal coefficient) {
    setPrice(this.price.multiply(coefficient).setScale(2, RoundingMode.UP));
    }
}
