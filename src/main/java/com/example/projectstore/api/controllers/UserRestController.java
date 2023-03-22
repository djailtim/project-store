package com.example.projectstore.api.controllers;
import com.example.projectstore.api.models.User;
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
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping(params = {"country"})
    public List<User> search(@RequestParam("country") String country) {
       return userService.findByCountry(country);
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }


    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
    return userService.update(id, user);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {userService.delete(id);
    }

}
