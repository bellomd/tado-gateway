package com.tado.gateway.api.dto;

import java.io.Serializable;

public class AuthRequestDto implements Serializable {

	private static final long serialVersionUID = -370592903909261321L;
	
	private final String username;
	private final String password;
	
	public AuthRequestDto(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
