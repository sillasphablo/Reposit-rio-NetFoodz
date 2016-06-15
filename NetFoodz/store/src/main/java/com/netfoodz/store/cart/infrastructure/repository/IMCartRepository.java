package com.netfoodz.store.cart.infrastructure.repository;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.common.domain.model.Identity;
import com.netfoodz.store.cart.domain.model.Cart;
import com.netfoodz.store.cart.domain.repository.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IMCartRepository implements CartRepository {

	private final Map<Identity, Cart> map = new HashMap<>();

	@Override
	public void save(Cart cart) {
		map.put(cart.getId(), cart);
	}

	@Override
	public Cart findByCustomerEmail(EmailAddress email) {
		return null;
	}
}
