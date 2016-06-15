package com.netfoodz.store.authentication.application.exception;

public class WebRequestException extends Exception {
	public WebRequestException() {
	}

	public WebRequestException(String s) {
		super(s);
	}

	public WebRequestException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public WebRequestException(Throwable throwable) {
		super(throwable);
	}

	public WebRequestException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
