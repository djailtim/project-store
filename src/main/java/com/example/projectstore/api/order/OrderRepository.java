package com.example.projectstore.api.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    void deleteById(Long userId);
}
