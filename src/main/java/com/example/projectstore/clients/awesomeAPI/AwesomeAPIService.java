package com.example.projectstore.clients.awesomeAPI;

import org.springframework.stereotype.Service;

@Service
public class AwesomeAPIService {
    private final AwesomeAPIRestRepository restRepository;

    public AwesomeAPIService(AwesomeAPIRestRepository restRepository) {
        this.restRepository = restRepository;
    }

    public ExchangeDTO search(String conversionCode) {
        return restRepository.search(conversionCode).get(0);
    }
}
