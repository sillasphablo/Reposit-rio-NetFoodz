package com.netfoodz.common.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DomainEventPublisher {

	private static final ThreadLocal<DomainEventPublisher> instance = new ThreadLocal<DomainEventPublisher>() {
		protected DomainEventPublisher initialValue() {
			return new DomainEventPublisher();
		}
	};

	private boolean publishing;

	private List<DomainEventHandler> subscribers;

	public static DomainEventPublisher instance() {
		return instance.get();
	}

	public <T> void publish(final T domainEvent) {
		if (!this.isPublishing() && this.hasSubscribers()) {

			try {
				this.setPublishing(true);

				Class<?> eventType = domainEvent.getClass();

				List<DomainEventHandler> allSubscribers = this.subscribers();

				for (DomainEventHandler subscriber : allSubscribers) {
					Class<?> subscribedToType = subscriber.subscribedToEventType();

					if (eventType == subscribedToType || subscribedToType == DomainEvent.class) {
						subscriber.handleEvent(domainEvent);
					}
				}

			} finally {
				this.setPublishing(false);
			}
		}
	}

	public void publishAll(Collection<DomainEvent> domainEvents) {
		domainEvents.forEach(this::publish);
	}

	public void reset() {
		if (!this.isPublishing()) {
			this.setSubscribers(null);
		}
	}

	public <T> void subscribe(DomainEventHandler<T> subscriber) {
		if (!this.isPublishing()) {
			this.ensureSubscribersList();

			this.subscribers().add(subscriber);
		}
	}

	private DomainEventPublisher() {
		super();

		this.setPublishing(false);
		this.ensureSubscribersList();
	}

	private void ensureSubscribersList() {
		if (!this.hasSubscribers()) {
			this.setSubscribers(new ArrayList());
		}
	}

	private boolean isPublishing() {
		return this.publishing;
	}

	private void setPublishing(boolean aFlag) {
		this.publishing = aFlag;
	}

	private boolean hasSubscribers() {
		return this.subscribers() != null;
	}

	private List<DomainEventHandler> subscribers() {
		return this.subscribers;
	}

	private void setSubscribers(List<DomainEventHandler> subscriberList) {
		this.subscribers = subscriberList;
	}
}
