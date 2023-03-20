package com.example.projectstore.clients.ibge;

import com.example.projectstore.clients.ibge.ibgeDTO.IdentityCountryDTO;
import com.example.projectstore.clients.ibge.ibgeDTO.NameCountryDTO;
import com.example.projectstore.clients.ibge.ibgeDTO.UnitMonetaryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter @Setter
@ToString
public class CountryCurrency {

    private String id;

    private String nameCountry;

    private String unityMonetary;
}
