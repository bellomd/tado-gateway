package com.tado.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tado.gateway.api.RefreshRequestDto;
import com.tado.gateway.api.dto.AuthRequestDto;
import com.tado.gateway.api.dto.SessionDto;
import com.tado.gateway.auth.TokenService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final TokenService tokenService;
	
	@Autowired
	public AuthenticationServiceImpl(final TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public SessionDto authenticate(final AuthRequestDto authRequestDto) {
		return tokenService.createToken(authRequestDto.getUsername(), authRequestDto.getPassword());
	}

	@Override
	public SessionDto refreshToken(final RefreshRequestDto refreshRequestDto) {
		return tokenService.refreshToken(refreshRequestDto.getRefreshToken());
	}
}
