package com.example.projectstore.api.controller;

import com.example.projectstore.clients.ibge.CountryCurrency;
import com.example.projectstore.clients.ibge.CountryRestRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CountryCurrencyRestController {

    private final CountryRestRepository restRepository;

    public CountryCurrencyRestController(CountryRestRepository restRepository) {
        this.restRepository = restRepository;
    }

    @GetMapping("/{countryCode}")
    public CountryCurrency search(@PathVariable String countryCode) {
        return this.restRepository.search(countryCode);
    }

}
