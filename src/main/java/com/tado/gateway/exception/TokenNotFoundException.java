package com.tado.gateway.exception;

public class TokenNotFoundException extends AbstractRuntimeException {

	private static final long serialVersionUID = -220190263630150349L;

	public TokenNotFoundException(final String message) {
		super(message);
	}
	
	public TokenNotFoundException(final Throwable cause) {
		super(cause);
	}
	
	public TokenNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
