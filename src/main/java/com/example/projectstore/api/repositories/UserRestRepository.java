package com.example.projectstore.api.repositories;

import com.example.projectstore.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRestRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
