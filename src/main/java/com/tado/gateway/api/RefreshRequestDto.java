package com.tado.gateway.api;

import java.io.Serializable;

public class RefreshRequestDto implements Serializable {

	private static final long serialVersionUID = -8894032858333023553L;
	
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
