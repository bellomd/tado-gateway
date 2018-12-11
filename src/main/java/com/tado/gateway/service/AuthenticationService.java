package com.tado.gateway.service;

import com.tado.gateway.api.RefreshRequestDto;
import com.tado.gateway.api.dto.AuthRequestDto;
import com.tado.gateway.api.dto.SessionDto;

public interface AuthenticationService {

	/**
	 * Create token for a user with the given information
	 * 
	 * @param authRequestDto
	 * @return SessionDto
	 */
	SessionDto authenticate(final AuthRequestDto authRequestDto);
	
	/**
	 * Refresh an existing token for the given refresh token
	 * 
	 * @param refreshRequestDto
	 * @return SessionDto
	 */
	SessionDto refreshToken(final RefreshRequestDto refreshRequestDto);
}
