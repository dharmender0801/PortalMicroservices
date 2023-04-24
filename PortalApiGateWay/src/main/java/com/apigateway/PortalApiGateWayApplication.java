package com.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
//@CrossOrigin(origins = "*")
public class PortalApiGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApiGateWayApplication.class, args);
	}

}
