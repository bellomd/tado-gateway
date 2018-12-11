package com.tado.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.tado.gateway.api.AuthenticationRestService;
import com.tado.gateway.api.dto.AuthRequestDto;
import com.tado.gateway.api.dto.SessionDto;
import com.tado.gateway.auth.AuthenticationTokenRequestInterceptor;
import com.tado.gateway.auth.HeaderRequestInterceptor;

@ActiveProfiles("DEV")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApplication.class)
public class AbstractRestIT {
	
	private static final String DEMO_USERNAME = "demo_username";
	private static final String DEMO_PASSWORD = "demo_password";

	@Value("${local.server.port:8080}")
	protected String port;

	@Value("${server.context-path:/api/v1}")
	protected String contextPath;

	protected String baseUrl;
	protected String authenticationUrl;
	protected RestTemplate restTemplate;
	
	@PostConstruct
	protected void init() {
		baseUrl = "http://localhost:" + port + contextPath;
		authenticationUrl = baseUrl + AuthenticationRestService.CREATE_TOKEN_URL;
		
		final AuthRequestDto requestDto = new AuthRequestDto(DEMO_USERNAME, DEMO_PASSWORD);
		final SessionDto sessionDto = getSecureRestTemplate(null).postForObject(
				authenticationUrl, requestDto, SessionDto.class);
		
		final String token = sessionDto.getValue();
		System.out.println("The token: " + token);
		
		restTemplate = getSecureRestTemplate(token);
	}

	protected String authenticate(String username, String password) {
		final AuthRequestDto requestDto = new AuthRequestDto(username, password);
		final SessionDto response = restTemplate.postForObject(authenticationUrl, requestDto, SessionDto.class);
		return response.getValue();
	}

	protected RestTemplate getSecureRestTemplate(final String secureToken) {
		
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Content-Type", MediaType.APPLICATION_JSON_VALUE));
		
		if (secureToken != null) {
			interceptors.add(new AuthenticationTokenRequestInterceptor(secureToken));
		}
		
		restTemplate.setInterceptors(interceptors);
		
		return restTemplate;
	}
}
