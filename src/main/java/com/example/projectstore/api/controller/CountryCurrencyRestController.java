package com.example.projectstore.api;

import com.example.projectstore.ibge.CountryCurrency;
import com.example.projectstore.ibge.CountryRestRepository;
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
