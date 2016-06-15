package com.netfoodz.store.authentication.domain.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Role {

	@NonNull
	private String name;
}
