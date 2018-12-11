package com.tado.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = GatewayApplication.BASE_PACKAGES)
public class GatewayApplication extends SpringBootServletInitializer {
	
	static final String BASE_PACKAGES = "com.tado";

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
