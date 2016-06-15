package com.netfoodz.store.cart.domain.repository;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.store.cart.domain.model.Cart;

public interface CartRepository {

	void save(Cart cart);

	Cart findByCustomerEmail(EmailAddress email);
}
