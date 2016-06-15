package com.netfoodz.common.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.regex.Pattern;

	@EqualsAndHashCode(of = "address")
public final class EmailAddress implements Serializable {

	@Getter
	private String address;

	public EmailAddress(String address) {
		boolean isValid = Pattern.matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", address);
		if (address == null) {
			throw new IllegalArgumentException("The email address is required");
		} else if (address.length() < 5 || address.length() > 100) {
			throw new IllegalArgumentException("Email address must be 100 characters or less");
		} else if (!isValid) {
			throw new IllegalArgumentException("Email address format is invalid");
		}

		this.address = address;
	}
}
