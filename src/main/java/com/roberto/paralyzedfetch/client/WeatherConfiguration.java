package com.roberto.paralyzedfetch.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Feign;
import feign.gson.GsonDecoder;

/**
 * Created by robertoduessmann on 2/5/19
 */
@Configuration
public class WeatherConfiguration
{
	@Bean
	public WeatherClient weatherClient(){
		return Feign.builder()
			.decoder(new GsonDecoder())
			.target(WeatherClient.class, "${weather.api.url}");
	}

	@Bean
	public Contract useFeignAnnotations() {
		return new Contract.Default();
	}
}
