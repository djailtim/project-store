package com.example.projectstore.api.services;

import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.UserRestRepository;
import com.example.projectstore.api.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRestRepository userRestRepository;
    private final ModelMapper modelMapper;

    private UserService(UserRestRepository userRestRepository){
        this.userRestRepository = userRestRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<UserResponse> findAll() {
        List<User> usersList = userRestRepository.findAll();
        return usersList.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList();
    }

}
