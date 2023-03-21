package com.example.projectstore.api.services;

import com.example.projectstore.awesomeAPI.AwesomeAPIObjectDTO;
import com.example.projectstore.api.repositories.AwesomeAPIRestRepository;
import org.springframework.stereotype.Service;

@Service
public class AwesomeAPIService {
    private final AwesomeAPIRestRepository restRepository;

    public AwesomeAPIService(AwesomeAPIRestRepository restRepository) {
        this.restRepository = restRepository;
    }

    public AwesomeAPIObjectDTO search(String conversionCode) {
        return restRepository.search(conversionCode).get(0);
    }
}
