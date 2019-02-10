package com.paralyzedfetch.client;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by robertoduessmann on 2/5/19
 */
@Configuration
public class WeatherConfiguration
{

	@Bean
	public Contract useFeignAnnotations() {
		return new Contract.Default();
	}
}
