package com.lingventa.openmeteo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("pl.lingventa.logging")
public class LingventaProperties {

    private boolean enabled;
}
