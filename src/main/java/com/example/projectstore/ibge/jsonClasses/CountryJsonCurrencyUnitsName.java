package com.example.projectstore.ibge.jsonClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class CountryJsonCurrencyUnitsName {
    @JsonProperty("ISO-4217-ALPHA")
    private String currencyUnitName;
}
