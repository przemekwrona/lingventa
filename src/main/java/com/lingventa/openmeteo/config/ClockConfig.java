package com.lingventa.openmeteo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class ClockConfig {
    public static final ZoneId WARSAW_ZONE_ID = ZoneId.of("Europe/Warsaw");

    @Bean
    public Clock clock() {
        return Clock.system(WARSAW_ZONE_ID);
    }
}
