package com.netfoodz.store.catalog.domain.model;

import com.netfoodz.common.domain.model.Entity;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Product extends Entity {

	@NonNull
	private String name;
	private BigDecimal price;
	private String description;
	private boolean inCatalog;
}
