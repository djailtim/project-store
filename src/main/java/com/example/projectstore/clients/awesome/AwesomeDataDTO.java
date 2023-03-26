package com.example.projectstore.clients.awesome;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AwesomeDataDTO {
    @JsonProperty("codein")
    private String currencyCode;
    @JsonProperty("ask")
    private BigDecimal coefficient;
}
