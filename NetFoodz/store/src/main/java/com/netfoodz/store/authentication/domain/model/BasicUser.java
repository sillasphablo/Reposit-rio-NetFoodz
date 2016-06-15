package com.netfoodz.store.authentication.domain.model;

import com.netfoodz.common.domain.model.EmailAddress;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class BasicUser implements User {

	private String name;
	private boolean enabled = true;
	@NonNull
	private EmailAddress email;
	@NonNull
	private String encryptedPassword;
	private List<Role> roles = new ArrayList<>();
}
