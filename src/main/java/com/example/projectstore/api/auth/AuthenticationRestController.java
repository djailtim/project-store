package com.example.projectstore.api.auth;

import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.services.AuthenticationService;
import com.example.projectstore.api.system.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationRestController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        return authenticationService.login(request.username(), request.password());
    }

}