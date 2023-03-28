package com.example.projectstore.api.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_orderline")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long userId;
    private Long productId;
    private String title;
    private Long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine orderLine)) return false;
        return getUserId().equals(orderLine.getUserId()) && getProductId().equals(orderLine.getProductId()) && getTitle().equals(orderLine.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getProductId(), getTitle());
    }
}



