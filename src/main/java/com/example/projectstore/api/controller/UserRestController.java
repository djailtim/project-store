package com.example.projectstore.api.controller;


import com.example.projectstore.api.dto.UserDTO;
import com.example.projectstore.api.responses.UserResponse;
import com.example.projectstore.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserDTO user){
        return userService.save(user);
    }

}
