package com.example.projectstore.clients.ibge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentityUnitMonetaryDTO {
    @JsonProperty("ISO-4217-ALPHA")
    private String alpha;
    @JsonProperty("ISO-4217-NUMERICO")
    private String number;
}
