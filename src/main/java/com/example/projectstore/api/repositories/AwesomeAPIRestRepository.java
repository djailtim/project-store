package com.example.projectstore.api.repositories;
import com.example.projectstore.awesomeAPI.AwesomeAPIObjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value = "awesomeAPIRestRepository", url = "https://economia.awesomeapi.com.br/json/")
public interface AwesomeAPIRestRepository {
    @GetMapping("/{conversionCode}")
    List<AwesomeAPIObjectDTO> search(@PathVariable String conversionCode);
}
