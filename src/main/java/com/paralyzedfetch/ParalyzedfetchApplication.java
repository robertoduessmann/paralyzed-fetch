package com.paralyzedfetch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ParalyzedfetchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParalyzedfetchApplication.class, args);
	}

}

