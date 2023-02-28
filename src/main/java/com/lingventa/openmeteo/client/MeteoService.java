package com.lingventa.openmeteo.client;

import com.lingventa.openmeteo.config.ClockConfig;
import com.openmeteo.api.model.MeteoStatisticSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MeteoService {

    public static final String SUNRISE = "sunrise";
    public static final String SUNSET = "sunset";
    public static final String PRECIPITATION_SUM = "precipitation_sum";
    private final MeteoClient meteoClient;

    public MeteoStatisticSummary getMeteStatistic(String latitude, String longitude) {
        var response = meteoClient.getMeteo(latitude, longitude, ClockConfig.WARSAW_ZONE_ID.getId(), List.of(SUNRISE, SUNSET, PRECIPITATION_SUM));
        return response.getBody();
    }
}
