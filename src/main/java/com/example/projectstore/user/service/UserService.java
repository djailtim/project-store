package com.example.projectstore.user.service;

import com.example.projectstore.user.model.User;
import com.example.projectstore.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //responsável pela lógica de negócios e coordenação entre outras camadas.
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    };

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
