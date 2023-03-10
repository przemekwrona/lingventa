package com.lingventa.openmeteo.statistic;

import com.lingventa.api.StatisticsApi;
import com.lingventa.api.model.MeteoStatistic;
import com.lingventa.openmeteo.aop.Log;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StatisticController implements StatisticsApi {

    private final StatisticService statisticService;

    @Log
    @Override
    public ResponseEntity<MeteoStatistic> getHistoricalMeteo(String latitude, String longitude) {
        return ResponseEntity.ok(statisticService.getHistoricalMeteo(latitude, longitude));
    }
}
