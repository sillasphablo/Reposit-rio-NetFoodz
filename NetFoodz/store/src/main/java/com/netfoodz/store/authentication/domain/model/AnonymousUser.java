package com.netfoodz.store.authentication.domain.model;

import com.netfoodz.common.domain.model.EmailAddress;

import java.util.ArrayList;
import java.util.List;

public class AnonymousUser implements User {

	private final List<Role> roles;

	public AnonymousUser() {
		roles = new ArrayList<>();
		roles.add(new Role("ROLE_ANONYMOUS"));
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getName() {
		return "anonymous";
	}

	@Override
	public EmailAddress getEmail() {
		return null;

	}

	@Override
	public List<Role> getRoles() {
		return roles;
	}
}
