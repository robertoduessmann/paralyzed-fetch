package com.roberto.paralyzedfetch.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.roberto.paralyzedfetch.model.WeatherDetailsDTO;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Created by robertoduessmann on 2/5/19
 */
@FeignClient(name="weather", url = "${weather.api.url}")
public interface WeatherClient {

	@RequestLine("GET /weather/{city}")
	@Headers("Accept: application/json")
	WeatherDetailsDTO getWeather(@Param("city") String city);
}