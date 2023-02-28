package com.lingventa.openmeteo.statistic;

import com.lingventa.api.StatisticsApi;
import com.lingventa.api.model.MeteoStatistic;
import lombok.AllArgsConstructor;
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
