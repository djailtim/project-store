package com.example.projectstore.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private String country;
    private String currency;

}
