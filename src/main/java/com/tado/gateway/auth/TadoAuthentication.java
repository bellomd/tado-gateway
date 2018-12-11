package com.tado.gateway.auth;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.tado.gateway.api.dto.HomeDto;
import com.tado.gateway.api.dto.SessionDto;

public class TadoAuthentication implements Authentication {

	private static final long serialVersionUID = -5187747489397998246L;
	
	private final SessionDto sessionDto;
	
	public TadoAuthentication(final SessionDto sessionDto) {
		this.sessionDto = sessionDto;
	}
	
	@Override
	public String getName() {
		return sessionDto.getUser().getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return sessionDto.getUser().getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		
	}
	
	public String getToken() {
		return sessionDto.getValue();
	}
	
	public String getRefreshToken() {
		return sessionDto.getRefreshToken().getValue();
	}
	
	public List<String> getHomes() {
		return sessionDto.getUser().getHomes().stream().map(HomeDto::getId).collect(Collectors.toList());
	}
}
