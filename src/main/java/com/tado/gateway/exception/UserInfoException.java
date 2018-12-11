package com.tado.gateway.exception;

public class UserInfoException extends AbstractRuntimeException {

	private static final long serialVersionUID = 271649031973357306L;

	public UserInfoException(final String message) {
		super(message);
	}
	
	public UserInfoException(final Throwable cause) {
		super(cause);
	}
	
	public UserInfoException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
