package com.netfoodz.common.domain.model;

import java.lang.reflect.ParameterizedType;

public interface DomainEventHandler<T> {

	void handleEvent(final T domainEvent);

	default Class<T> subscribedToEventType() {
		return (Class) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
