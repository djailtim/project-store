package com.example.projectstore.api.repositories;
import com.example.projectstore.api.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    List<OrderLine> getAllByUserId(Long userId);
}
