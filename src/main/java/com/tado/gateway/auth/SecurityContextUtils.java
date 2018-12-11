package com.tado.gateway.auth;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityContextUtils {

	private SecurityContextUtils() {
	}

	public static TadoAuthentication getAuthentication() {
		
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext != null) {
			
			final Authentication authentication = securityContext.getAuthentication();
			
			if (authentication instanceof TadoAuthentication) {
				return (TadoAuthentication) authentication;
			}
		}
		
		return null;
	}
	
	public static String getLoggedToken() {
		
		final TadoAuthentication tadoAuthentication = getAuthentication();
		
		if (tadoAuthentication != null) {
			return tadoAuthentication.getToken();
		}
		
		return null;
	}
	
	public static String getLoggedRefreshToken() {
		
		final TadoAuthentication tadoAuthentication = getAuthentication();
		
		if (tadoAuthentication != null) {
			return tadoAuthentication.getRefreshToken();
		}
		
		return null;
	}
	
	public static List<String> getLoggedUserHomes() {
		
		final TadoAuthentication tadoAuthentication = getAuthentication();
		
		if (tadoAuthentication != null) {
			return tadoAuthentication.getHomes();
		}
		
		return null;
	}
}
