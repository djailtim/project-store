package com.example.projectstore.ibge.controller;

import com.example.projectstore.ibge.model.CountryCurrency;
import com.example.projectstore.ibge.repository.CountryRestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CountryCurrencyRestController {

    private final CountryRestRepository restRepository;

    public CountryCurrencyRestController(CountryRestRepository restRepository) {
        this.restRepository = restRepository;
    }

    @GetMapping("/{countryCode}")
    public List<CountryCurrency> search(@PathVariable String countryCode) {
        return this.restRepository.search(countryCode);
    }

}
