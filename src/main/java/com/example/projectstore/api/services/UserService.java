package com.example.projectstore.api.services;

import com.example.projectstore.api.resquests.UserRequest;
import com.example.projectstore.api.exceptions.DuplicatedEmailException;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.responses.UserResponse;
import com.example.projectstore.clients.ibge.CountryService;
import com.example.projectstore.clients.ibge.dto.CurrencyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CountryService countryService;

    private UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,  CountryService countryService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.countryService = countryService;
        this.modelMapper = new ModelMapper();
    }

    public List<UserResponse> findAll() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList();
    }

    public UserResponse save(UserRequest userRequest) {
        Optional<User> userExists = this.userRepository.findByEmail(userRequest.getEmail());
        if(userExists.isPresent()) {
            throw new DuplicatedEmailException("E-mail já cadastrado.");
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User userCreated = modelMapper.map(userRequest, User.class);
        CurrencyDTO currencyDTO = countryService.search(userRequest.getCountryCode());
        userCreated.setCurrency(currencyDTO.getUnityMonetary());
        userCreated.setCountry(currencyDTO.getNameCountry());
        userRepository.save(userCreated);
        return modelMapper.map(userCreated, UserResponse.class);
    }

    public UserResponse findById(Long id) {
        User user = findUser(id);
        return modelMapper.map(user, UserResponse.class);
    }

    private User findUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return modelMapper.map(user, User.class);
    }

    public UserResponse update(Long id, UserRequest userRequest) {
        User user = this.findUser(id);
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        CurrencyDTO currencyDTO = countryService.search(userRequest.getCountryCode());
        user.setCountry(currencyDTO.getNameCountry());
        user.setCurrency(currencyDTO.getUnityMonetary());
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }


    public void delete(Long id) {
        this.findUser(id);
        userRepository.deleteById(id);
    }

    public List<UserResponse> findByCountry(String country) {
        List<User> usersList = this.userRepository.findByCountryContainingIgnoreCase(country);
        return usersList.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList();
    }
}
