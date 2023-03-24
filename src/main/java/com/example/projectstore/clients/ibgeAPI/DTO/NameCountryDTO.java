package com.example.projectstore.clients.ibgeAPI.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameCountryDTO {
    @JsonProperty("abreviado")
    private String abreviado;
    @JsonProperty("abreviado-EN")
    private String abreviadoEN;
    @JsonProperty("abreviado-ES")
    private String abreviadoES;
}
