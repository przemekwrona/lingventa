package com.lingventa.openmeteo.client;

import com.openmeteo.api.model.MeteoStatistic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MeteoService {

    private static final String EUROPE_WARSAW_TIMEZONE = "Europe/Warsaw";
    private final MeteoClient meteoClient;

    public MeteoStatistic getMeteStatistic(String latitude, String longitude) {
        var properties = List.of("sunrise", "sunset", "precipitation_probability_mean");
        var response = meteoClient.getMeteo(latitude, longitude, EUROPE_WARSAW_TIMEZONE, properties);
        return response.getBody();
    }
}
