package com.netfoodz.common.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
public class Identity implements Serializable {

	@Getter
	private String id;
	
	public static Identity generate(){
		return new Identity(UUID.randomUUID().toString());
	}

	@Override
	public String toString() {
		return id;
	}
}
