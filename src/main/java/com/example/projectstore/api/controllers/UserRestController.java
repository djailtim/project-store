package com.example.projectstore.api.controllers;
import com.example.projectstore.api.DTO.UserDTO;
import com.example.projectstore.api.responses.UserResponse;
import com.example.projectstore.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserDTO user){
        return userService.save(user);
    }

    @GetMapping
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @GetMapping(params = {"country"})
    public List<UserResponse> search(@RequestParam("country") String country) {
       return userService.findByCountry(country);
    }
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return userService.findById(id);
    }


    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserDTO user) {
    return userService.update(id, user);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {userService.delete(id);
    }

}
