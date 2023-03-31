package com.example.projectstore.clients.ibge;

import com.example.projectstore.clients.ibge.dto.CountryCurrencyDTO;
import com.example.projectstore.clients.ibge.dto.CurrencyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRestRepository countryRestRepository;

    public CountryService(CountryRestRepository countryRestRepository){
        this.countryRestRepository = countryRestRepository;
    }
    public CurrencyDTO search(String countryCode) {
        List<CountryCurrencyDTO> countryList =  countryRestRepository.search(countryCode);
        CountryCurrencyDTO countryCurrencyDTO = countryList.get(0);
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setId(countryCurrencyDTO.getId().getAlpha2());
        currencyDTO.setNameCountry(countryCurrencyDTO.getNameCountry().getAbreviado());
        currencyDTO.setUnityMonetary(countryCurrencyDTO.getUnityMonetary().get(0).getId().getAlpha());
        return currencyDTO;
    }
}
