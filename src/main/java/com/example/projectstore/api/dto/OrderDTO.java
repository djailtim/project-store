package com.example.projectstore.api.order;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {

    private Long user_id;
    @OneToMany
    private List<OrderLine> orderLineList;

    private String currency;
    private BigDecimal finalPrice;


}
