package com.netfoodz.store.cart.domain.event;

import com.netfoodz.common.domain.model.AbstractDomainEvent;
import com.netfoodz.common.domain.model.Identity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartCleared extends AbstractDomainEvent {

	private Identity customerId;
}
