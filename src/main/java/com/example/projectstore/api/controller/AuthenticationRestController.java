package com.example.projectstore.api.controller;

import com.example.projectstore.api.responses.AuthenticationResponse;
import com.example.projectstore.api.resquests.AuthenticationRequest;
import com.example.projectstore.api.services.AuthenticationService;
import com.example.projectstore.api.services.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final LogoutService logoutService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        return authenticationService.login(request.username(), request.password());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response){
        logoutService.logout(request, response);

    }

}
