package com.example.projectstore.api.models;

import lombok.*;

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
