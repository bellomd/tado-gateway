package com.tado.gateway.exception;

public class AuthenticationException extends AbstractRuntimeException {

	private static final long serialVersionUID = -6280092589968996442L;

	public AuthenticationException(final String message) {
		super(message);
	}
	
	public AuthenticationException(final Throwable cause) {
		super(cause);
	}
	
	public AuthenticationException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
