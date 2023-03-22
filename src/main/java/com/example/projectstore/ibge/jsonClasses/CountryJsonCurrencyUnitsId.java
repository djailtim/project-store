package com.example.projectstore.ibge.jsonClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class CountryJsonCurrencyUnitsId {
    @JsonProperty("id")
    private CountryJsonCurrencyUnitsName currencyUnityName;
}
