package com.example.projectstore.clients.ibge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class CountryCurrency {
    private String id;

    private String nameCountry;

    private String unityMonetary;

}
