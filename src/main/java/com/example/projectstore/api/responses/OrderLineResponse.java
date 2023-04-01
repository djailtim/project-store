package com.example.projectstore.api.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderLineResponse {

    private String title;
    private Long quantity;

    private Long id;

}



