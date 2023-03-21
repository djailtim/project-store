package com.example.projectstore.clients.ibge.ibgeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryCurrencyDTO {

    @JsonProperty("id")
    private IdentityCountryDTO id;
    @JsonProperty("nome")
    private NameCountryDTO nameCountry;
    @JsonProperty("unidades-monetarias")
    private List<UnitMonetaryDTO> unityMonetary;


}
