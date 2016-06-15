package com.netfoodz.common.domain.model;

import java.util.Date;

public class AbstractDomainEvent implements DomainEvent {

	private Date occurredOn;

	public AbstractDomainEvent() {
		occurredOn = new Date();
	}

	@Override
	public Date occurredOn() {
		return occurredOn;
	}
}
