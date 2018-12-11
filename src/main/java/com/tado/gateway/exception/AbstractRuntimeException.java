package com.tado.gateway.exception;

public class AbstractRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 7183567385530119663L;

	public AbstractRuntimeException(final String message) {
		super(message);
	}
	
	public AbstractRuntimeException(final Throwable cause) {
		super(cause);
	}
	
	public AbstractRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
