package com.example.projectstore.ibge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class CountryCurrency {
    @JsonProperty("abreviado")
    private String countryName;

    @JsonProperty("ISO-4217-ALPHA")
    private String currencyId;

    @JsonProperty("nome")
    private String currencyName;

}
