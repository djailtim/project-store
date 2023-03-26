package com.example.projectstore.api.controller;
import com.example.projectstore.api.services.AwesomeService;
import com.example.projectstore.clients.awesome.AwesomeDataDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/conversion")
public class AwesomeController {

    private final AwesomeService awesomeService;

    public AwesomeController(AwesomeService awesomeService) {
        this.awesomeService = awesomeService;
    }

    @GetMapping("/{conversionCode}")
    public AwesomeDataDTO search(@PathVariable("conversionCode") String conversionCode){
       return awesomeService.search(conversionCode);
    }
}
