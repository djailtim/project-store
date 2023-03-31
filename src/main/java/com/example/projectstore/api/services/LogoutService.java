package com.example.projectstore.api.services;

import com.example.projectstore.api.exceptions.NotFoundException;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.TokenRepository;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.system.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public LogoutService(JwtService jwtService, UserRepository userRepository, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
        var authentication = new UsernamePasswordAuthenticationToken(email, user.getPassword());
        logout(request, response, authentication);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            return;
        }
        final String jwt = header.substring(7);
        var storedToken = tokenRepository.findByToken(jwt);
        storedToken.orElseThrow(() -> new NotFoundException("token nao encontrado")).setExpired(true);
        tokenRepository.save(storedToken.get());


    }
}
