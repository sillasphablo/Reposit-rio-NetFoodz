package com.netfoodz.store.cart.domain.exception;

public class CartAlreadyEmptyException extends Exception {
	public CartAlreadyEmptyException() {
	}

	public CartAlreadyEmptyException(String s) {
		super(s);
	}

	public CartAlreadyEmptyException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public CartAlreadyEmptyException(Throwable throwable) {
		super(throwable);
	}

	public CartAlreadyEmptyException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
