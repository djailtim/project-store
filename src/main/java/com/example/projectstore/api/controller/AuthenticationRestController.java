package com.example.projectstore.api.controller;

import com.example.projectstore.api.responses.AuthenticationResponse;
import com.example.projectstore.api.resquests.AuthenticationRequest;
import com.example.projectstore.api.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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
