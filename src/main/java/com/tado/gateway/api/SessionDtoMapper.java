package com.tado.gateway.api;

import java.io.Serializable;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.tado.gateway.api.dto.RefreshTokenDto;
import com.tado.gateway.api.dto.SessionDto;

public class SessionDtoMapper implements Serializable {

	private static final long serialVersionUID = 1880494321996686678L;

	public static SessionDto convert(final OAuth2AccessToken accessToken) {
		
		final SessionDto sessionDto = new SessionDto();
		sessionDto.setValue(accessToken.getValue());
		sessionDto.setTokenType(accessToken.getTokenType());
		sessionDto.setExpiration(accessToken.getExpiration());
		sessionDto.setScope(accessToken.getScope());
		
		final RefreshTokenDto refreshToken = new RefreshTokenDto();
		refreshToken.setValue(accessToken.getRefreshToken().getValue());
		sessionDto.setRefreshToken(refreshToken);
		
		return sessionDto;
	}

}
