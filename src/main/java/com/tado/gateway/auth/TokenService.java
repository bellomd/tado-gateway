package com.tado.gateway.auth;

import com.tado.gateway.api.dto.SessionDto;

public interface TokenService {
	
	/**
	 * Create token for the given username and password.
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return SessionDto
	 */
	SessionDto createToken(final String username, final String password);
	
	/**
	 * Refresh an existing token with the given token
	 * 
	 * @param refreshToken
	 * @return SessionDto
	 */
	SessionDto refreshToken(final String refreshToken);
	
	/**
	 * Get save sessionDto for the given token key
	 * 
	 * @param tokenKey
	 * @return SessionDto
	 */
	SessionDto getSessionDto(final String token);
}
