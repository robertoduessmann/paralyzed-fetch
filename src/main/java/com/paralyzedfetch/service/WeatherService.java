package com.paralyzedfetch.service;

import com.paralyzedfetch.model.WeatherDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by robertoduessmann on 2/5/19
 */
@Service
@AllArgsConstructor
public class WeatherService {

    private CityReader cityReader;
    private ParalyzedFetchWeather paralyzedFetchWeather;

    public List<WeatherDTO> getWeather() {
        return paralyzedFetchWeather.fetchWeather(cityReader.read())
                .stream()
                .map(weather -> new WeatherDTO(weather.getCity(), weather.getTemperature()))
                .collect(Collectors.toList());
    }

}
