package com.roberto.paralyzedfetch.controller;

import com.roberto.paralyzedfetch.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by robertoduessmann on 2/5/19
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping("cities")
    public ResponseEntity getWeatherCities() {
        return ResponseEntity.ok(service.getWeather());
    }
}
