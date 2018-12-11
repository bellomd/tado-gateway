package com.tado.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

@Configuration
public class RestConfiguration {
	
	@Bean
	@ConfigurationProperties("security.oauth2.client")
	protected ResourceOwnerPasswordResourceDetails resourceOwnerPasswordResourceDetails() {
		return new ResourceOwnerPasswordResourceDetails();
	}
	
	@Bean
	protected OAuth2RestTemplate tokenRestTemplate() {
		return new OAuth2RestTemplate(resourceOwnerPasswordResourceDetails());
	}
}
