package com.netfoodz.store.authentication.domain.exception;

public class NoUserAuthenticatedException extends RuntimeException {
	public NoUserAuthenticatedException() {
	}

	public NoUserAuthenticatedException(String s) {
		super(s);
	}

	public NoUserAuthenticatedException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public NoUserAuthenticatedException(Throwable throwable) {
		super(throwable);
	}

	public NoUserAuthenticatedException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
