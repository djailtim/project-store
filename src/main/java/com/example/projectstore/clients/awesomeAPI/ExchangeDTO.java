package com.example.projectstore.clients.awesomeAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeDTO {

    @JsonProperty("code")
    private String originalCountryCode;

    @JsonProperty("codein")
    private String toConvertCountryCode;

    @JsonProperty("bid")
    private BigDecimal coefficient;


}
