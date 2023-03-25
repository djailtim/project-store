package com.example.projectstore.clients.ibge;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "countryRestRepository", url = "${ibgeapi.url}")
public interface CountryRestRepository {
    @GetMapping("/{countryCode}")
    CountryCurrency search(@PathVariable String countryCode);
}
