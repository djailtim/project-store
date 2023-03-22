package com.example.projectstore.ibge.jsonClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class CountryJsonName {
    @JsonProperty("abreviado")
    private String countryName;
}
