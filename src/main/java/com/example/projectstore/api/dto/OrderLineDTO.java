package com.example.projectstore.api.order;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderLineDTO {

    private Long product_id;
    private Long quantity;

}



