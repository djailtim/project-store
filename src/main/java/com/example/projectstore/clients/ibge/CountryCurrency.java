package com.example.projectstore.clients.ibge;

import com.example.projectstore.clients.ibge.ibgeDTO.NameCountryDTO;
import com.example.projectstore.clients.ibge.ibgeDTO.UnitMonetaryDTO;
import com.example.projectstore.clients.ibge.ibgeDTO.IdentityCountryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryCurrency {

    @JsonProperty("id")
    private IdentityCountryDTO id;
    @JsonProperty("nome")
    private NameCountryDTO nameCountry;
    @JsonProperty("unidades-monetarias")
    private List<UnitMonetaryDTO> unityMonetary;


}
