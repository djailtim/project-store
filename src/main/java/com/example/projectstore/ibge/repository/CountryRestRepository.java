package com.example.projectstore.ibge.repository;

import com.example.projectstore.ibge.model.CountryCurrency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "countryRestRepository", url = "${ibgeapi.url}")
public interface CountryRestRepository {
    @GetMapping("/{countryCode}")
    List<CountryCurrency> search(@PathVariable String countryCode);
}
