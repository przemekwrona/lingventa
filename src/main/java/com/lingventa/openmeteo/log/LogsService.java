package com.lingventa.openmeteo.log;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
@AllArgsConstructor
public class LogsService {
    private final LogsRepository logsRepository;
    private final Clock clock;

    public void logInfoCallServiceToGetStatistic() {
        Logs logs = Logs.builder()
                .type(LogType.METEO_STATISTIC)
                .date(Instant.now(clock))
                .query("{}")
                .build();

        logsRepository.save(logs);
    }
}
