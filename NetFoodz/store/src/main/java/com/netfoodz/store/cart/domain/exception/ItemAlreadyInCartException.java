package com.netfoodz.store.cart.domain.exception;

public class ItemAlreadyInCartException extends RuntimeException {
	public ItemAlreadyInCartException() {
	}

	public ItemAlreadyInCartException(String s) {
		super(s);
	}

	public ItemAlreadyInCartException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ItemAlreadyInCartException(Throwable throwable) {
		super(throwable);
	}

	public ItemAlreadyInCartException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
