package com.example.projectstore.api.auth;

import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.system.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationRestController {

    private final UserRepository userJpaRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        var authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(authentication);
        User user = userJpaRepository.findByEmail(request.username()).orElseThrow();
        String token = jwtService.createToken(user);
        return  new AuthenticationResponse(user.getId(), token);
    }

}
