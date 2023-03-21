package com.example.projectstore.api.repositories;

import com.example.projectstore.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCountry(String nationality);
}
