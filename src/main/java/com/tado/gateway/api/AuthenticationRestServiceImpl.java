package com.tado.gateway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tado.gateway.api.dto.AuthRequestDto;
import com.tado.gateway.api.dto.SessionDto;
import com.tado.gateway.service.AuthenticationService;

@RestController
public class AuthenticationRestServiceImpl implements AuthenticationRestService {

	private final AuthenticationService authenticationRestService;
	
	@Autowired
	public AuthenticationRestServiceImpl(final AuthenticationService authenticationRestService) {
		this.authenticationRestService = authenticationRestService;
	}
	
	@Override
	@PostMapping(
			path = CREATE_TOKEN_URL,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public SessionDto authenticate(@RequestBody final AuthRequestDto authRequestDto) {
		return authenticationRestService.authenticate(authRequestDto);
	}

	@Override
	@PostMapping(
			path = REFRESH_TOKEN_URL,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public SessionDto refreshToken(@RequestBody final RefreshRequestDto refreshRequestDto) {
		return authenticationRestService.refreshToken(refreshRequestDto);
	}
}
