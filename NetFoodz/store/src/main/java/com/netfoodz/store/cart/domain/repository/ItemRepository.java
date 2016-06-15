package com.netfoodz.store.cart.domain.repository;

import com.netfoodz.store.cart.domain.model.Item;

import java.util.Collection;

public interface ItemRepository {

	void save(Item item);

	void delete(Item item);

	void deleteAll(Collection<Item> itemsRemoved);
}
