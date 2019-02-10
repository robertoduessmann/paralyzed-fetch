package com.paralyzedfetch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by robertoduessmann on 2/5/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDetailsDTO
{
	private String city;
	private String temperature;
	private String wind;
}
