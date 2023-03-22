package com.example.projectstore.api.services;

import com.example.projectstore.api.models.Conversion;
import com.example.projectstore.api.repositories.AwesomeAPIRestRepository;
import org.springframework.stereotype.Service;

@Service
public class AwesomeAPIService {
    private final AwesomeAPIRestRepository restRepository;

    public AwesomeAPIService(AwesomeAPIRestRepository restRepository) {
        this.restRepository = restRepository;
    }

    public Conversion search(String conversionCode) {
        return restRepository.search(conversionCode).get(0);
    }
}
