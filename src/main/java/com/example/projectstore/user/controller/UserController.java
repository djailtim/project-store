package com.example.projectstore.user.controller;

import com.example.projectstore.user.model.User;
import com.example.projectstore.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public User create(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping("/all")
    public List<User> findAll(){
        return this.userService.findAll();
    }

    @GetMapping("/{name}")
    public List<User> search(@PathVariable String name) {
        return this.userService.findByName(name);
    }
}
