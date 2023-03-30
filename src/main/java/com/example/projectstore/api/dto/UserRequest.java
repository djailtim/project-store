package com.example.projectstore.api.dto;

import com.example.projectstore.api.model.Role;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class UserRequest {


    @Pattern(regexp = "^[^0-9!@#&()–{}:;',?*~$^+=<>\\[\\]\\\\\\.\\/]{2,255}$")
    private String name;
    @Pattern(regexp = "^[\\w.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String password;
    private String country;
    private String currency;
    private Role role;

}
