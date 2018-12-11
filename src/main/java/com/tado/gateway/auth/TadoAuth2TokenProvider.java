package com.tado.gateway.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.tado.gateway.api.dto.SessionDto;
import com.tado.gateway.exception.TokenNotFoundException;

@Service
public class TadoAuth2TokenProvider implements Auth2TokenProvider {
	
	private static final String TOKEN_BEARER = "Bearer ";

	private final TokenService tokenService;
	
	@Autowired
	public TadoAuth2TokenProvider(final TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		final Optional<Object> tokenObject = Optional.ofNullable(authentication.getPrincipal());
		
		if (!tokenObject.isPresent()) {
			
			throw new TokenNotFoundException("Authorization token is not found");
		}
		
		String token = (String) tokenObject.get();
		
		if (token.startsWith(TOKEN_BEARER)) {
			token = token.replace(TOKEN_BEARER, "");
		}
		
		final SessionDto sessionDto = tokenService.getSessionDto(token);
		
		if (sessionDto == null) {
			throw new BadCredentialsException("Invalid token");
		}
		
		return new TadoAuthentication(sessionDto);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
