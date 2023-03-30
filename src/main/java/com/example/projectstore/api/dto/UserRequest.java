package com.example.projectstore.api.dto;

import com.example.projectstore.api.model.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private String country;
    private String currency;
    private Role role;

}
