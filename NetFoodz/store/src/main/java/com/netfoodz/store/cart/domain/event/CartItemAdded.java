package com.netfoodz.store.cart.domain.event;

import com.netfoodz.common.domain.model.AbstractDomainEvent;
import com.netfoodz.common.domain.model.Identity;
import com.netfoodz.store.cart.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemAdded extends AbstractDomainEvent {

	private Identity customerId;
	private Item item;
}
