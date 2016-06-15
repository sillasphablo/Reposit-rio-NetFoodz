package com.netfoodz.store.authentication.domain.repository;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.store.authentication.domain.model.BasicUser;

public interface BasicUserRepository {

	BasicUser findByEmail(EmailAddress emailAddress);
}
