package com.example.projectstore.api.services;
import com.example.projectstore.api.DTO.UserDTO;
import com.example.projectstore.api.models.User;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    public UserResponse save(UserDTO userDTO) {
        if (userRepository.findAll().stream().noneMatch(user -> user.getName().equals(userDTO.getName()) &&
                user.getEmail().equals(userDTO.getEmail()))){
            userRepository.save(modelMapper.map(userDTO, User.class));
            return modelMapper.map(userDTO, UserResponse.class);
        }else return null;
    }

    public List<UserResponse> findAll(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user ->  modelMapper.map(user, UserResponse.class)).toList();
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return modelMapper.map(user, UserResponse.class);
    }

    public UserResponse update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setCurrency(userDTO.getCurrency());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setCountry(userDTO.getCountry());
            userRepository.save(user);
            return modelMapper.map(userDTO, UserResponse.class);
        }else return null;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserResponse> findByCountry(String country) {
        List<User> userList = userRepository.findByCountry(country);
        return userList.stream().map(user ->  modelMapper.map(user, UserResponse.class)).toList();
    }
}

