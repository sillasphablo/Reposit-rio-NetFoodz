package com.netfoodz.store.cart.application;

import com.netfoodz.store.authentication.application.annotation.Authenticated;
import com.netfoodz.store.cart.application.command.AddItemToCartCommand;
import com.netfoodz.store.cart.application.command.ClearCartCommand;
import com.netfoodz.store.cart.application.command.RemoveItemFromCartCommand;
import com.netfoodz.store.cart.domain.exception.CartAlreadyEmptyException;
import com.netfoodz.store.cart.domain.exception.ProductNotInCartException;
import com.netfoodz.store.cart.domain.model.Cart;
import com.netfoodz.store.cart.domain.model.Item;
import com.netfoodz.store.cart.domain.repository.CartRepository;
import com.netfoodz.store.cart.domain.repository.ItemRepository;
import com.netfoodz.store.cart.domain.service.CartProductService;
import com.netfoodz.store.catalog.domain.model.Product;
import com.netfoodz.store.catalog.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CartApplicationService {

	private ItemRepository itemRepository;
	private CartProductService cartProductService;
	private CartRepository cartRepository;
	private ProductRepository productRepository;

	@Autowired
	public CartApplicationService(ItemRepository itemRepository, CartProductService cartProductService,
								  CartRepository cartRepository, ProductRepository productRepository) {
		this.itemRepository = itemRepository;
		this.cartProductService = cartProductService;
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
	}

	@Authenticated
	public void addItemToCart(AddItemToCartCommand command) {
		Product product = productRepository.findById(command.getProductId());

		if(product == null) {
			throw new IllegalArgumentException("Product of id " + command.getProductId() + " does not exist");
		}

		Cart cart = cartRepository.findByCustomerEmail(command.getCustomerEmail());

		Item item = cartProductService.addNewItem(cart, product, command.getAmount(), command.getObservation());

		itemRepository.save(item);
	}

	@Authenticated
	public void removeItemFromCart(RemoveItemFromCartCommand command) throws ProductNotInCartException {
		Cart cart = cartRepository.findByCustomerEmail(command.getCustomerEmail());

		Item item = cart.remove(command.getProductId());

		itemRepository.delete(item);
	}

	public void clearCart(ClearCartCommand command) throws CartAlreadyEmptyException {
		Cart cart = cartRepository.findByCustomerEmail(command.getCustomerEmail());

		Collection<Item> itemsRemoved = cart.clear();

		itemRepository.deleteAll(itemsRemoved);
	}
}
