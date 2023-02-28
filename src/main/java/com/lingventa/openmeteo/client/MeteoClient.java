package com.lingventa.openmeteo.client;

import com.openmeteo.api.ForecastApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "open-meteo-api", url = "http://api.open-meteo.com/v1/")
public interface MeteoClient extends ForecastApi {
}
