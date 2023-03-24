package com.example.projectstore.clients.awesomeAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value = "awesomeAPIRestRepository", url = "${awesomeapi.url}")
public interface AwesomeAPIRestRepository {
    @GetMapping("/{conversionCode}")
    List<ExchangeDTO> search(@PathVariable String conversionCode);
}
