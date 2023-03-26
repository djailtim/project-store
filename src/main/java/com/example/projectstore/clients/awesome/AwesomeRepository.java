package com.example.projectstore.clients.awesome;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(value = "awesomeRepository", url = "${awesomeapi.url}")
public interface AwesomeRepository {
    @GetMapping("/{conversionCode}")
    List<AwesomeDataDTO> search(@PathVariable("conversionCode") String conversionCode);

}
