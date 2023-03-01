package com.lingventa.openmeteo.statistic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StatisticControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldNotThrowErrorAndReturnEmptyResponse() throws Exception {
        this.mvc.perform(get("/statistics?latitude=52.52&longitude=13.41"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotThrowErrorIfCoordinatesAreNegativeNumber() throws Exception {
        this.mvc.perform(get("/statistics?latitude=-52.52&longitude=-13.41"))
                .andExpect(status().isOk());
    }

    static Stream<Arguments> provideIncorrectStatisticURL() {
        return Stream.of(
                Arguments.of("/statistics?latitude=52.52", "Required request parameter 'longitude' for method parameter type String is not present"),
                Arguments.of("/statistics?longitude=13.41", "Required request parameter 'latitude' for method parameter type String is not present"),
                Arguments.of("/statistics?latitude=52,52&longitude=13.41", "Parametr latitude (52,52) must match \"\\-?[0-9]*\\.[0-9]*\""),
                Arguments.of("/statistics?latitude=52.5dwa&longitude=13.41", "Parametr latitude (52.5dwa) must match \"\\-?[0-9]*\\.[0-9]*\""),
                Arguments.of("/statistics?latitude=52.52&longitude=13.4jeden", "Parametr longitude (13.4jeden) must match \"\\-?[0-9]*\\.[0-9]*\"")
        );
    }

    @ParameterizedTest
    @MethodSource("provideIncorrectStatisticURL")
    void shouldThrowErrorIfURLIsIncorrect(String url, String error) throws Exception {
        this.mvc.perform(get(url))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value(error));
    }

}