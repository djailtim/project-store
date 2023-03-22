package com.example.projectstore.ibge.jsonClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class CountryJsonCode {
    @JsonProperty("ISO-3166-1-ALPHA-2")
    private String countryNameCode;
}
