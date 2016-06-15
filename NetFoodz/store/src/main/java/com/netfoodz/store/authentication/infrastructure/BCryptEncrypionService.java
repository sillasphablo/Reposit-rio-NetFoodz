package com.netfoodz.store.authentication.infrastructure;

import com.netfoodz.store.authentication.application.EncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncrypionService implements EncryptionService {

	@Override
	public String encryptedValue(String plainTextValue) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(plainTextValue);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(rawPassword, encodedPassword);
	}
}
