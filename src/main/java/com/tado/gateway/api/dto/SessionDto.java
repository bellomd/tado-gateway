package com.tado.gateway.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDto implements Serializable {

	private static final long serialVersionUID = 2896818273370292241L;

	private String value;

	private Date expiration;

	private String tokenType;

	private RefreshTokenDto refreshToken;

	private Set<String> scope;

	private UserDto user;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public RefreshTokenDto getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(RefreshTokenDto refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
}
