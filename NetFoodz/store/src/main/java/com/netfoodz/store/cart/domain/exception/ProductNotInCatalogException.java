package com.netfoodz.store.cart.domain.exception;

public class ProductNotInCatalogException extends RuntimeException {
	public ProductNotInCatalogException() {
	}

	public ProductNotInCatalogException(String s) {
		super(s);
	}

	public ProductNotInCatalogException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ProductNotInCatalogException(Throwable throwable) {
		super(throwable);
	}

	public ProductNotInCatalogException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
