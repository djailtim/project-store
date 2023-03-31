package com.example.projectstore.api.controller;

import com.example.projectstore.api.resquests.UserRequest;
import com.example.projectstore.api.responses.UserResponse;
import com.example.projectstore.api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserRestController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserResponse create(@RequestBody @Valid UserRequest user) {
//        return userService.save(user);
//    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody @Valid UserRequest user) {
        return userService.update(id, user);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping(params = {"country"})
    public List<UserResponse> findByCountry(@RequestParam String country) {
        return userService.findByCountry(country);
    }

}
