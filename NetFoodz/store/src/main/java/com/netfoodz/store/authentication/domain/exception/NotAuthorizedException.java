package com.netfoodz.store.authentication.domain.exception;

public class NotAuthorizedException extends RuntimeException {
	public NotAuthorizedException() {
	}

	public NotAuthorizedException(String s) {
		super(s);
	}

	public NotAuthorizedException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public NotAuthorizedException(Throwable throwable) {
		super(throwable);
	}

	public NotAuthorizedException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
