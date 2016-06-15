package com.netfoodz.store.cart.domain.model;

import com.netfoodz.common.domain.model.Entity;
import com.netfoodz.common.domain.model.Identity;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Item extends Entity {

	@NonNull
	private Identity productId;
	@NonNull
	private Identity cartId;
	@NonNull
	private BigDecimal price;
	private Integer amount = 1;
	private String observation;

	public BigDecimal getTotalPrice() {
		return price.multiply(BigDecimal.valueOf(amount));
	}
}
