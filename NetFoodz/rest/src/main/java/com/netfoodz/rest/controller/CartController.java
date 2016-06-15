package com.netfoodz.rest.controller;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.common.domain.model.Identity;
import com.netfoodz.store.authentication.application.SessionService;
import com.netfoodz.store.cart.application.CartApplicationService;
import com.netfoodz.store.cart.application.command.AddItemToCartCommand;
import com.netfoodz.store.cart.application.command.ClearCartCommand;
import com.netfoodz.store.cart.application.command.RemoveItemFromCartCommand;
import com.netfoodz.store.cart.domain.exception.CartAlreadyEmptyException;
import com.netfoodz.store.cart.domain.exception.ProductNotInCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private SessionService sessionService;
	private CartApplicationService cartService;

	@Autowired
	public CartController(SessionService sessionService, CartApplicationService cartService) {
		this.sessionService = sessionService;
		this.cartService = cartService;
	}


	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{productId}", method = RequestMethod.POST)
	public void addProductToCart(@PathVariable("productId") String productId,
								 @RequestParam(defaultValue = "1") Integer amount,
								 @RequestParam(required = false) String observation) {

		AddItemToCartCommand command = new AddItemToCartCommand(new Identity(productId), email());
		command.setAmount(amount);
		command.setObservation(observation);
		cartService.addItemToCart(command);
	}


	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public void removeProductFromCart(@PathVariable("productId") String productId) throws ProductNotInCartException {
		RemoveItemFromCartCommand command = new RemoveItemFromCartCommand(new Identity(productId), email());
		cartService.removeItemFromCart(command);
	}


	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/clear", method = RequestMethod.POST)
	public void clearCart() throws ProductNotInCartException, CartAlreadyEmptyException {
		ClearCartCommand command = new ClearCartCommand(email());
		cartService.clearCart(command);
	}

	private EmailAddress email() {
		return sessionService.getEmailFromLoggedUser();
	}
}
