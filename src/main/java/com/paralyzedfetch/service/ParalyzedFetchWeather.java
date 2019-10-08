package com.paralyzedfetch.service;

import com.paralyzedfetch.client.WeatherClient;
import com.paralyzedfetch.model.WeatherDTO;
import com.paralyzedfetch.model.WeatherDetailsDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by robertoduessmann on 2/5/19
 */
@Service
@AllArgsConstructor
public class ParalyzedFetchWeather {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParalyzedFetchWeather.class);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);
    private static final long TIMEOUT_MINUTES = 60;

    private WeatherClient weatherClient;

    @PreDestroy
    private void shutdown() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(TIMEOUT_MINUTES, TimeUnit.MINUTES);
    }

    public List<WeatherDTO> fetchWeather(List<String> cities) {
        return cities.stream()
                .map(c -> executorService.submit(() -> weatherClient.getWeather(c)))
                .parallel()
                .map(this::getWeather)
                .collect(Collectors.toList());
    }

    private WeatherDTO getWeather(Future<WeatherDetailsDTO> weatherDetailsDTOFuture){
        try {
            WeatherDetailsDTO weatherDetailsDTO = weatherDetailsDTOFuture.get();
            return new WeatherDTO(weatherDetailsDTO.getCity(), weatherDetailsDTO.getTemperature());
        } catch (Exception e) {
            LOGGER.error("Error occurred", e);
            return new WeatherDTO();
        }
    }
}
