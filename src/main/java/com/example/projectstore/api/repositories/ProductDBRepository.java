package com.example.projectstore.api.repositories;

import com.example.projectstore.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDBRepository extends JpaRepository<Product, Long> {
}
