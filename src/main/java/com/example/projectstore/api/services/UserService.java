package com.example.projectstore.api.services;
import com.example.projectstore.api.models.User;
import com.example.projectstore.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return (userRepository.findAll().stream().noneMatch(u -> u.equals(user))) ? userRepository.save(user) : null;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(Long id, User newUser) {
        User oldUser = findById(id);
        oldUser.setCurrency(newUser.getCurrency());
        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setCountry(newUser.getCountry());
        return userRepository.save(oldUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByCountry(String country) {
        return userRepository.findByCountry(country);
    }
}

