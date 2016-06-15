package com.netfoodz.store.authentication.domain.model;

import com.netfoodz.common.domain.model.EmailAddress;

import java.util.List;

public interface User {

	boolean isEnabled();
	String getName();
	EmailAddress getEmail();
	List<Role> getRoles();
}
