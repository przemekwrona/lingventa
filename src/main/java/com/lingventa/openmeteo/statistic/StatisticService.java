package com.lingventa.openmeteo.statistic;

import com.lingventa.api.model.MeteoStatistic;
import com.lingventa.openmeteo.client.MeteoService;
import com.openmeteo.api.model.MeteoStatisticSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatisticService {

    private final MeteoService meteoService;
    public MeteoStatistic getHistoricalMeteo(String latitude, String longitude) {
        var response = meteoService.getMeteStatistic(latitude, longitude);
        return buildMeteoStatisticResponse(response);
    }
    private MeteoStatistic buildMeteoStatisticResponse(MeteoStatisticSummary response) {
        return Optional.ofNullable(response)
                .map(MeteoStatisticSummary::getDaily)
                .map(meteo -> new MeteoStatistic()
                        .dates(meteo.getTime())
                        .sunrise(meteo.getSunrise())
                        .sunset(meteo.getSunset())
                        .precipitationSum(meteo.getPrecipitationSum()))
                .orElse(new MeteoStatistic()
                        .dates(List.of())
                        .sunrise(List.of())
                        .sunset(List.of())
                        .precipitationSum(List.of()));
    }
}
