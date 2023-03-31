package com.example.projectstore.api.responses;
import com.example.projectstore.api.model.OrderLine;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderResponse {

    @OneToMany
    private List<OrderLine> orderLineList;

    private String currency;
    private BigDecimal finalPrice;


}
