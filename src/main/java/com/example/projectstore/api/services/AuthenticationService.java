package com.example.projectstore.api.services;

import com.example.projectstore.api.auth.AuthenticationResponse;
import com.example.projectstore.api.exceptions.UserNotFoundException;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.system.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        }
        var authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);

        String token = jwtService.createToken(user.get());
        return new AuthenticationResponse(user.get().getId(), token);
    }

}
