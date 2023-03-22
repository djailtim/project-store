package com.example.projectstore.user.repository;

import com.example.projectstore.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(@PathVariable String name);
}
