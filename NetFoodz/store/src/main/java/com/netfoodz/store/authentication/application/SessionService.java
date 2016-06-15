package com.netfoodz.store.authentication.application;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.store.authentication.domain.model.User;

public interface SessionService {

	void login(User user);

	void logout();

	EmailAddress getEmailFromLoggedUser();

	boolean isAnonymousUser();
}
