package com.netfoodz.common.domain.model;

import lombok.Data;

@Data
public abstract class Entity {

	public Entity() {
		id = Identity.generate();
	}

	protected Identity id;

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
