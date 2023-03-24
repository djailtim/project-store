package com.example.projectstore.clients.ibgeAPI;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter @Setter
@ToString
public class CurrencyDTO {

    private String id;

    private String nameCountry;

    private String unityMonetary;
}
