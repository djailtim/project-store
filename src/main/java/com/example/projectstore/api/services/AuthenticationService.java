package com.example.projectstore.api.services;

import com.example.projectstore.api.exceptions.NotFoundException;
import com.example.projectstore.api.model.TokenValidator;
import com.example.projectstore.api.repositories.TokenRepository;
import com.example.projectstore.api.responses.AuthenticationResponse;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.system.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        List<TokenValidator> tokenValidatorList = tokenRepository.findAll().stream()
                .filter(tokenValidator -> tokenValidator.getUserId().equals(user.getId())).toList();
        if (!tokenValidatorList.isEmpty()){
            List<TokenValidator> tokenValidatorListExpired = tokenValidatorList.stream().peek(tokenFromList -> tokenFromList.setExpired(true)).toList();
            tokenRepository.saveAll(tokenValidatorListExpired);
        }

        var authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);
        String token = jwtService.createToken(user);


        TokenValidator tokenValidator = new TokenValidator();
        tokenValidator.setExpired(false);
        tokenValidator.setUserId(user.getId());
        tokenValidator.setToken(token);
        tokenValidator.setRole(user.getRole());

        tokenRepository.save(tokenValidator);
        return new AuthenticationResponse(user.getId(), token);
    }

}
