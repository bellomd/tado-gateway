package com.tado.gateway.auth;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import com.tado.gateway.api.ApiUri;
import com.tado.gateway.api.SessionDtoMapper;
import com.tado.gateway.api.dto.SessionDto;
import com.tado.gateway.api.dto.UserDto;
import com.tado.gateway.exception.AuthenticationException;
import com.tado.gateway.exception.UserInfoException;

@Service
public class TokenServiceImpl implements TokenService {
	
	private final ResourceOwnerPasswordResourceDetails resourceOwnerPasswordResourceDetails;
	private final OAuth2RestTemplate tokenRestTemplate;
	private final ApiUri apiUri;
	
	//TODO:
	// This should be replaced with Redis or Hazelcast
	private final ConcurrentHashMap<String, SessionDto> tokenStore = new ConcurrentHashMap<>();
	
	@Autowired
	public TokenServiceImpl(
			final ResourceOwnerPasswordResourceDetails resourceOwnerPasswordResourceDetails,
			final OAuth2RestTemplate tokenRestTemplate, 
			final ApiUri apiUri) {
		
		this.resourceOwnerPasswordResourceDetails = resourceOwnerPasswordResourceDetails;
		this.tokenRestTemplate = tokenRestTemplate;
		this.apiUri = apiUri;
	}

	@Override
	public SessionDto createToken(final String username, final String password) {
		
		resourceOwnerPasswordResourceDetails.setUsername(username);
		resourceOwnerPasswordResourceDetails.setPassword(password);
		
		final OAuth2AccessToken accessToken = Optional.of(tokenRestTemplate.getAccessToken())
				.orElseThrow(() -> new AuthenticationException("Token cannot be created."));
		
		final SessionDto sessionDto = SessionDtoMapper.convert(accessToken);
		
		final UserDto userDto = Optional.of(tokenRestTemplate.getForObject(
				apiUri.getUserInfoUri(), 
				UserDto.class))
				.orElseThrow(() -> new UserInfoException("Cannot retrieve user information."));
		
		sessionDto.setUser(userDto);
		tokenStore.put(getTokenKey(sessionDto.getValue()), sessionDto);
		
		return sessionDto;
	}

	@Override
	public SessionDto refreshToken(String refreshToken) {
		return null;
	}

	@Override
	public SessionDto getSessionDto(final String token) {
		final String tokenKey = getTokenKey(token);
		return tokenStore.get(tokenKey);
	}
	
	private String getTokenKey(final String token) {
		return token.substring(0, token.indexOf('.'));
	}
}
