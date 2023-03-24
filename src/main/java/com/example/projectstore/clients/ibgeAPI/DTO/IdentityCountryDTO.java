package com.example.projectstore.clients.ibgeAPI.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentityCountryDTO {
    @JsonProperty("M49")
    private Long id;
    @JsonProperty("ISO-3166-1-ALPHA-2")
    private String alpha2;
    @JsonProperty("ISO-3166-1-ALPHA-3")
    private String alpha3;
}
