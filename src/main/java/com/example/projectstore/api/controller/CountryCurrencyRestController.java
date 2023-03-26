package com.example.projectstore.api.controller;

import com.example.projectstore.clients.ibge.CountryCurrency;
import com.example.projectstore.clients.ibge.CountryRestRepository;
import com.example.projectstore.clients.ibge.dto.CountryCurrencyDTO;
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
        CountryCurrency currencyDTO = new CountryCurrency();
        currencyDTO.setId(countryCurrencyDTO.getId().getAlpha2());
        currencyDTO.setNameCountry(countryCurrencyDTO.getNameCountry().getAbreviado());
        currencyDTO.setUnityMonetary(countryCurrencyDTO.getUnityMonetary().get(0).getId().getAlpha());
        return currencyDTO;
    }

}
