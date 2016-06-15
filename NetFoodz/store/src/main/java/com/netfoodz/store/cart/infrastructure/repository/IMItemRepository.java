package com.netfoodz.store.cart.infrastructure.repository;

import com.netfoodz.common.domain.model.Identity;
import com.netfoodz.store.cart.domain.model.Item;
import com.netfoodz.store.cart.domain.repository.ItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class IMItemRepository implements ItemRepository {

	private final Map<Identity, Item> map = new HashMap<>();

	@Override
	public void save(Item item) {
		Assert.notNull(item);
		map.put(item.getId(), item);
	}

	@Override
	public void delete(Item item) {
		Assert.notNull(item);
		map.remove(item.getId());
	}

	@Override
	public void deleteAll(Collection<Item> itemsRemoved) {
		itemsRemoved.stream()
				.filter(item -> item != null)
				.forEach(this::delete);
	}
}
