package com.lingventa.openmeteo.statistic;

import lombok.AllArgsConstructor;
import org.openapitools.api.StatisticsApi;
import org.openapitools.model.MeteoStatistic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StatisticController implements StatisticsApi {

    @Override
    public ResponseEntity<MeteoStatistic> getHistoricalMeteo(String longitude, String latitude) {
        return ResponseEntity.ok(new MeteoStatistic());
    }
}
