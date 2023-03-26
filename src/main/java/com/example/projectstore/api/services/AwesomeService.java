package com.example.projectstore.api.services;
import com.example.projectstore.clients.awesome.AwesomeDataDTO;
import com.example.projectstore.clients.awesome.AwesomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AwesomeService {

    private final AwesomeRepository awesomeRepository;

    public AwesomeService(AwesomeRepository awesomeRepository) {
        this.awesomeRepository = awesomeRepository;
    }

    public AwesomeDataDTO search(String conversionCode){
        List<AwesomeDataDTO> conversionDTOList = awesomeRepository.search(conversionCode);
        return conversionDTOList.get(0);

    }
}
