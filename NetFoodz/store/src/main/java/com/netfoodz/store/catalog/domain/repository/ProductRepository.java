package com.netfoodz.store.catalog.domain.repository;

import com.netfoodz.common.domain.model.Identity;
import com.netfoodz.store.catalog.domain.model.Product;

import java.util.List;

public interface ProductRepository {

	Product findById(Identity productId);

	List<Product> findInCatalog();
}
