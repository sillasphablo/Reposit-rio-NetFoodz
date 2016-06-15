package com.netfoodz.store.cart.domain.exception;

public class ProductNotInCartException extends Exception {
	public ProductNotInCartException() {
	}

	public ProductNotInCartException(String s) {
		super(s);
	}

	public ProductNotInCartException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ProductNotInCartException(Throwable throwable) {
		super(throwable);
	}

	public ProductNotInCartException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
