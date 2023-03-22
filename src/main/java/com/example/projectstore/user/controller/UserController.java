package com.example.projectstore.user.controller;

import com.example.projectstore.user.model.User;
import com.example.projectstore.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{name}")
        public List<User> search(@PathVariable String name) {
            return this.userService.findByName(name);
        }
}
