package com.example.projectstore.api.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {



}
