package com.netfoodz.store.cart.domain.model;

import com.netfoodz.common.domain.model.DomainEventPublisher;
import com.netfoodz.common.domain.model.Entity;
import com.netfoodz.common.domain.model.Identity;
import com.netfoodz.store.cart.domain.event.CartCleared;
import com.netfoodz.store.cart.domain.event.CartItemAdded;
import com.netfoodz.store.cart.domain.event.CartItemRemoved;
import com.netfoodz.store.cart.domain.exception.CartAlreadyEmptyException;
import com.netfoodz.store.cart.domain.exception.ItemAlreadyInCartException;
import com.netfoodz.store.cart.domain.exception.ProductNotInCartException;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class Cart extends Entity {

	@NonNull
	private Identity customerId;
	private Map<Identity, Item> items = new HashMap<>();

	public Item add(Identity productId, BigDecimal price, Integer amount, String observation)
			throws ItemAlreadyInCartException {

		if (items.containsKey(productId)) {
			throw new ItemAlreadyInCartException();
		}

		Item item = new Item(productId, this.getId(), price);
		item.setAmount(amount);
		item.setObservation(observation);
		items.put(productId, item);
		DomainEventPublisher.instance().publish(new CartItemAdded(customerId, item));

		return item;

	}

	public Item remove(Identity productId) throws ProductNotInCartException {
		if (!items.containsKey(productId)) {
			throw new ProductNotInCartException();
		}

		Item itemRemoved = items.remove(productId);

		DomainEventPublisher.instance().publish(new CartItemRemoved(customerId, itemRemoved));

		return itemRemoved;
	}

	public Collection<Item> clear() throws CartAlreadyEmptyException {
		if(items.isEmpty())
			throw new CartAlreadyEmptyException();

		Collection<Item> itemList = items.values();
		items.clear();

		DomainEventPublisher.instance().publish(new CartCleared(customerId));
		return itemList;
	}

	public Integer size() {
		return items.size();
	}

	public boolean contains(Identity productId) {
		return items.containsKey(productId);
	}

	public BigDecimal getTotalPrice() {
		BigDecimal price = BigDecimal.ZERO;
		for (Item item : items.values()) {
			price = price.add(item.getTotalPrice());
		}
		return price;
	}
}
