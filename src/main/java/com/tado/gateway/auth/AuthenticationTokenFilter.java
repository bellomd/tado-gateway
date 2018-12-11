package com.tado.gateway.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import com.tado.gateway.exception.TokenNotFoundException;

public class AuthenticationTokenFilter extends RequestHeaderAuthenticationFilter {

	private static final String HEADER_KEY = "Authorization";
	
	public AuthenticationTokenFilter(final AuthenticationManager authenticationManager) {
		
		setAuthenticationManager(authenticationManager);
		setExceptionIfHeaderMissing(false);
		setPrincipalRequestHeader(HEADER_KEY);
		setCheckForPrincipalChanges(false);
		setContinueFilterChainOnUnsuccessfulAuthentication(false);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if (!hasAuthorizationHeader(request, response)) {
			throw new TokenNotFoundException("Authorization header or value does not exists");
		}

		try {
			
			super.doFilter(request, response, chain);
			
		} catch(AuthenticationException e) {
			
			final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpServletResponse.getOutputStream().print("Authentication error.");
			
		}
	}


	private boolean hasAuthorizationHeader(final ServletRequest request, final ServletResponse response) {
		
		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		return httpServletRequest.getHeader(HEADER_KEY) != null;
	}
}
