package com.lingventa.openmeteo.aop;

import com.lingventa.openmeteo.log.LogsService;
import com.lingventa.openmeteo.properties.LingventaProperties;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class LoggingAspect {

    private final LogsService logsService;
    private final LingventaProperties lingventaProperties;

    @Before(value = "@annotation(Log)")
    public void audit(JoinPoint joinPoint, Log Log) {
        if (lingventaProperties.isEnabled()) {
            String latitude = (String) joinPoint.getArgs()[0];
            String longitude = (String) joinPoint.getArgs()[1];
            logsService.logInfoCallServiceToGetStatistic(latitude, longitude);
        }
    }
}
