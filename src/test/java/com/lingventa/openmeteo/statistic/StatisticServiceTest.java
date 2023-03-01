package com.lingventa.openmeteo.statistic;

import com.lingventa.api.model.MeteoStatistic;
import com.lingventa.openmeteo.client.MeteoService;
import com.openmeteo.api.model.MeteoData;
import com.openmeteo.api.model.MeteoStatisticSummary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

    @Mock
    private MeteoService meteoService;

    @InjectMocks
    private StatisticService statisticService;

    @Test
    void shouldReturnEmptyMeasuresIfMeteoServiceReturnsNull() {
        //given
        when(meteoService.getMeteStatistic(any(), any())).thenReturn(null);

        //when
        MeteoStatistic meteoStatistic = statisticService.getHistoricalMeteo("52.354", "21.020");

        //then
        assertThat(meteoStatistic).isNotNull();
        assertThat(meteoStatistic.getDates()).isEmpty();
        assertThat(meteoStatistic.getSunrise()).isEmpty();
        assertThat(meteoStatistic.getSunset()).isEmpty();
        assertThat(meteoStatistic.getPrecipitationSum()).isEmpty();
    }

    private static Stream<Arguments> provideInvalidMeteoResponses() {
        return Stream.of(
                Arguments.of(new MeteoStatisticSummary()),
                Arguments.of(new MeteoStatisticSummary().daily(null)));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMeteoResponses")
    void shouldReturnEmptyMeasuresIfMeteoServiceReturnsInvalidResponse(MeteoStatisticSummary meteoStatisticSummary) {
        //given
        when(meteoService.getMeteStatistic(any(), any())).thenReturn(meteoStatisticSummary);

        //when
        MeteoStatistic meteoStatistic = statisticService.getHistoricalMeteo("52.354", "21.020");

        //then
        assertThat(meteoStatistic).isNotNull();
        assertThat(meteoStatistic.getDates()).isEmpty();
        assertThat(meteoStatistic.getSunrise()).isEmpty();
        assertThat(meteoStatistic.getSunset()).isEmpty();
        assertThat(meteoStatistic.getPrecipitationSum()).isEmpty();
    }

    @Test
    void shouldReturnEmptyMeasuresIfMeteoServiceReturnsInvalidResponse() {
        //given
        LocalDate now = LocalDate.of(2023, 2, 28);
        String sunrise = "2023-02-28T06:53";
        String sunset = "2023-02-28T17:44";
        MeteoData meteoData = new MeteoData()
                .time(List.of(now))
                .sunrise(List.of(sunrise))
                .sunset(List.of(sunset))
                .precipitationSum(List.of(1.5f));

        MeteoStatisticSummary meteoStatisticSummary = new MeteoStatisticSummary().daily(meteoData);

        //and
        when(meteoService.getMeteStatistic(any(), any())).thenReturn(meteoStatisticSummary);

        //when
        MeteoStatistic meteoStatistic = statisticService.getHistoricalMeteo("52.354", "21.020");

        //then
        assertThat(meteoStatistic).isNotNull();
        assertThat(meteoStatistic.getDates()).hasSize(1);
        assertThat(meteoStatistic.getDates().get(0)).isEqualTo(now);

        assertThat(meteoStatistic.getSunrise()).hasSize(1);
        assertThat(meteoStatistic.getSunrise().get(0)).isEqualTo(sunrise);

        assertThat(meteoStatistic.getSunset()).hasSize(1);
        assertThat(meteoStatistic.getSunset().get(0)).isEqualTo(sunset);

        assertThat(meteoStatistic.getPrecipitationSum()).hasSize(1);
        assertThat(meteoStatistic.getPrecipitationSum().get(0)).isEqualTo(1.5f);
    }
}