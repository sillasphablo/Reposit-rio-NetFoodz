package com.netfoodz.store.authentication.application;

public interface EncryptionService {

	String encryptedValue(String rawPassword);

	boolean matches(CharSequence rawPassword, String encodedPassword);
}
