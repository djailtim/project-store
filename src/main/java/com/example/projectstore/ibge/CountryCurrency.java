package com.example.projectstore.ibge;

import com.example.projectstore.ibge.jsonClasses.CountryJsonCode;
import com.example.projectstore.ibge.jsonClasses.CountryJsonCurrencyUnitsId;
import com.example.projectstore.ibge.jsonClasses.CountryJsonName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class CountryCurrency {
    @JsonProperty("id")
    private CountryJsonCode countryJsonCode;
    @JsonProperty("nome")
    private CountryJsonName countryJsonName;
    @JsonProperty("unidades-monetarias")
    private List<CountryJsonCurrencyUnitsId> countryJsonCurrencyUnitsId;

}
