package com.example.projectstore.clients.ibgeAPI;

import com.example.projectstore.clients.ibgeAPI.DTO.CountryCurrencyDTO;
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
    private CurrencyDTO search(@PathVariable String countryCode) {
        List<CountryCurrencyDTO>  countryList =  this.restRepository.search(countryCode);
        CountryCurrencyDTO countryCurrencyDTO = countryList.get(0);
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setId(countryCurrencyDTO.getId().getAlpha2());
        currencyDTO.setNameCountry(countryCurrencyDTO.getNameCountry().getAbreviado());
        currencyDTO.setUnityMonetary(countryCurrencyDTO.getUnityMonetary().get(0).getId().getAlpha());
        return currencyDTO;
    }

}
