package com.tado.gateway.auth;

/**
 * 
 * Interceptor for RestTemplate.
 * 
 * Add authenticationToken in request headers.
 *
 */
public class AuthenticationTokenRequestInterceptor extends HeaderRequestInterceptor {

	private final static String HEADER_NAME = "Authorization";

	public AuthenticationTokenRequestInterceptor(String token) {
		super(HEADER_NAME, token);
	}

}