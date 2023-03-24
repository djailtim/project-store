package com.example.projectstore.clients.awesomeAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversion")
public class AwesomeAPIRestController {
    private final AwesomeAPIService service;

    public AwesomeAPIRestController(AwesomeAPIService service) {
        this.service = service;
    }

    @GetMapping("/{conversionCode}")
    public ExchangeDTO search(@PathVariable String conversionCode){
       return service.search(conversionCode);
    }
}
