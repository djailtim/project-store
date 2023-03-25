package com.example.projectstore.api.services;

import com.example.projectstore.api.dto.UserDTO;
import com.example.projectstore.api.exceptions.DuplicatedEmailException;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.UserRestRepository;
import com.example.projectstore.api.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public UserResponse save(UserDTO userDTO) {
        Optional<User> userExists = this.userRestRepository.findByEmail(userDTO.getEmail());
        if (userExists.isPresent()){
            throw new DuplicatedEmailException("E-mail já cadastrado.");
        }
        User userCreated = this.userRestRepository.save(modelMapper.map(userDTO, User.class));
        return modelMapper.map(userCreated, UserResponse.class);
    }
}
