package com.example.projectstore.api.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
        @GetMapping("/storelogin")
    public String boot(){
        return "login-page";
    }


    @GetMapping("/login")
    public String bootDois(){
            return "home";
    }
}
