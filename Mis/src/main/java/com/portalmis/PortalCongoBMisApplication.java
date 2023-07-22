
package com.portalmis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebMvc
public class PortalCongoBMisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalCongoBMisApplication.class, args);
	}

}
