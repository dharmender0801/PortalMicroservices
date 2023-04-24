package com.countrywise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CountrywiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountrywiseApplication.class, args);
	}

}
