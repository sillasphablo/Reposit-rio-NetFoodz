package com.netfoodz.store.cart.infrastructure.repository;

import com.netfoodz.common.domain.model.Identity;
import com.netfoodz.store.catalog.domain.model.Product;
import com.netfoodz.store.catalog.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class IMProductRepository implements ProductRepository {

	private final Map<Identity, Product> map = new HashMap<>();

	@PostConstruct
	public void init() {
		Product coca = new Product("Coca-cola");
		coca.setPrice(BigDecimal.valueOf(3));
		coca.setDescription("A delicious coca-cola");

		Product sunday = new Product("Sunday");
		sunday.setInCatalog(true);
		sunday.setPrice(BigDecimal.valueOf(5));
		sunday.setDescription("A delicious sunday");

		map.put(coca.getId(), coca);
		map.put(sunday.getId(), sunday);
	}

	@Override
	public Product findById(Identity productId) {
		return map.get(productId);
	}

	@Override
	public List<Product> findInCatalog() {
		return map.values()
				.stream()
				.filter(Product::isInCatalog)
				.collect(Collectors.toList());
	}
}
