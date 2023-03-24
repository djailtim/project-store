package com.example.projectstore.clients.ibgeAPI;

import com.example.projectstore.clients.ibgeAPI.DTO.CountryCurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(value = "countryRestRepository", url = "${ibgeapi.url}")
public interface CountryRestRepository {
    @GetMapping("/{countryCode}")
    List<CountryCurrencyDTO> search(@PathVariable String countryCode);
}
