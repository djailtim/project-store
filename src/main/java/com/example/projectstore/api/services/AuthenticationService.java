package com.example.projectstore.api.services;

import com.example.projectstore.api.responses.AuthenticationResponse;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.system.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        var authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);

        String token = jwtService.createToken(user);
        return new AuthenticationResponse(user.getId(), token);
    }

}
