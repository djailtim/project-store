package com.example.projectstore.api.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse {
    private String name;

    private String country;

    private String currency;
}
