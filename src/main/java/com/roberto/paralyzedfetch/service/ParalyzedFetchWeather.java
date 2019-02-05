package com.roberto.paralyzedfetch.service;

import com.roberto.paralyzedfetch.client.WeatherClient;
import com.roberto.paralyzedfetch.model.WeatherDetailsDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by robertoduessmann on 2/5/19
 */
@Service
@AllArgsConstructor
public class ParalyzedFetchWeather {

    private static final ForkJoinPool executorService = new ForkJoinPool();
    private static final long TIMEOUT_MINUTES = 60;

    private WeatherClient weatherClient;

    @PreDestroy
    private void shutdown() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(TIMEOUT_MINUTES, TimeUnit.MINUTES);
    }

    public List<WeatherDetailsDTO> fetchWeather(List<String> cities) {
        List<WeatherDetailsDTO> weathers = Collections.synchronizedList(new ArrayList<>());
        cities.forEach(city -> executorService.submit(() -> weathers.add(weatherClient.getWeather(city))));
        executorService.awaitQuiescence(TIMEOUT_MINUTES, TimeUnit.MINUTES);
        return weathers;
    }
}
