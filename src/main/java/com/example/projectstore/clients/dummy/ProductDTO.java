package com.example.projectstore.clients.dummy;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    @JsonProperty("id")
    private Long productDTOid;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private String price;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("category")
    private String category;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("stock")
    private Integer stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO that)) return false;
        return getProductDTOid().equals(that.getProductDTOid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductDTOid());
    }

    public boolean nullAttribute() {
        return this.getProductDTOid() == null || this.getTitle() == null || this.getCategory() == null || this.getPrice() == null ||
                this.getBrand() == null || this.getStock() == null || this.getDescription() == null || this.getThumbnail() == null;
    }
}
