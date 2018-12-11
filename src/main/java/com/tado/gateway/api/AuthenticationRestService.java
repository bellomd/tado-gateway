package com.tado.gateway.api;

import com.tado.gateway.api.dto.AuthRequestDto;
import com.tado.gateway.api.dto.SessionDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/public/auth")
public interface AuthenticationRestService {
	
	static final String CREATE_TOKEN_URL = "/public/auth/create/token";
	static final String REFRESH_TOKEN_URL = "/public/auth/refresh/token";

	/**
	 * Create token for a user with the given information
	 * 
	 * @param authRequestDto
	 * @return SessionDto
	 */
	@ApiOperation(
            value = CREATE_TOKEN_URL,
            notes = "Create token for a user with the given information",
            httpMethod = "POST",
    		consumes = "application/json",
            produces = "application/json",
            response = SessionDto.class)
	SessionDto authenticate(final AuthRequestDto authRequestDto);
	
	/**
	 * Refresh an existing token for the given refresh token
	 * 
	 * @param refreshRequestDto 
	 * @return SessionDto
	 */
	@ApiOperation(
            value = REFRESH_TOKEN_URL,
            notes = "Create token for a user with the given information",
            httpMethod = "POST",
    		consumes = "application/json",
            produces = "application/json",
            response = SessionDto.class)
	SessionDto refreshToken(final RefreshRequestDto refreshRequestDto);
}
