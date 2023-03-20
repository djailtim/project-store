package com.example.projectstore.api.controllers;

import com.example.projectstore.clients.ibge.CountryCurrency;
import com.example.projectstore.clients.ibge.ibgeDTO.CountryCurrencyDTO;
import com.example.projectstore.clients.ibge.CountryRestRepository;
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
    public CountryCurrency search(@PathVariable String countryCode) {
        List<CountryCurrencyDTO>  countryList =  this.restRepository.search(countryCode);
        CountryCurrencyDTO countryCurrencyDTO = countryList.get(0);
        CountryCurrency countryCurrency = new CountryCurrency();
        countryCurrency.setId(countryCurrencyDTO.getId().getAlpha2());
        countryCurrency.setNameCountry(countryCurrencyDTO.getNameCountry().getAbreviado());
        countryCurrency.setUnityMonetary(countryCurrencyDTO.getUnityMonetary().get(0).getId().getAlpha());
        return countryCurrency;
    }

}
