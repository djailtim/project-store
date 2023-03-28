package com.example.projectstore.api.repositories;

import com.example.projectstore.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsDBRepository extends JpaRepository<Product, Long> {

    @Override
    <S extends Product> List<S> saveAll(Iterable<S> entities);
    Optional<Product> findProductByProductDTOid (Long productDTOId);

    List<Product> findByCategory(String categoryName);

    List<Product> searchByTitleStartingWith(String name);
}
