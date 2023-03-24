package com.example.projectstore.clients.ibgeAPI.DTO;

import com.example.projectstore.clients.ibgeAPI.DTO.IdentityUnitMonetaryDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitMonetaryDTO {

    private IdentityUnitMonetaryDTO id;
    private String nome;
}
