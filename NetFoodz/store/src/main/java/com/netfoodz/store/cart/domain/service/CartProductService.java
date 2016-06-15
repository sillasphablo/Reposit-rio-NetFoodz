package com.netfoodz.store.cart.domain.service;

import com.netfoodz.store.cart.domain.exception.ItemAlreadyInCartException;
import com.netfoodz.store.cart.domain.exception.ProductNotInCatalogException;
import com.netfoodz.store.cart.domain.model.Cart;
import com.netfoodz.store.cart.domain.model.Item;
import com.netfoodz.store.catalog.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class CartProductService {

	public Item addNewItem(Cart cart, Product product, Integer amount, String observation)
			throws ItemAlreadyInCartException {

		if (!product.isInCatalog()) {
			throw new ProductNotInCatalogException();
		}

		return cart.add(product.getId(), product.getPrice(), amount, observation);
	}
}
