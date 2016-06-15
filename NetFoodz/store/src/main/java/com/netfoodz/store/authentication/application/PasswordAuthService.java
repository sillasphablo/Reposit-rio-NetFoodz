package com.netfoodz.store.authentication.application;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.store.authentication.domain.model.BasicUser;
import com.netfoodz.store.authentication.domain.repository.BasicUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordAuthService {

	private SessionService sessionService;
	private EncryptionService encryptionService;
	private BasicUserRepository basicUserRepository;

	@Autowired
	public PasswordAuthService(SessionService sessionService, EncryptionService encryptionService,
									 BasicUserRepository basicUserRepository) {
		this.sessionService = sessionService;
		this.encryptionService = encryptionService;
		this.basicUserRepository = basicUserRepository;
	}

	public boolean authenticateWithPassword(EmailAddress emailAddress, String password) {
		BasicUser userDetails = basicUserRepository.findByEmail(emailAddress);

		boolean success = userDetails != null && userDetails.isEnabled() &&
				encryptionService.matches(password, userDetails.getEncryptedPassword());

		if (success) {
			sessionService.login(userDetails);
		}

		return success;
	}
}
