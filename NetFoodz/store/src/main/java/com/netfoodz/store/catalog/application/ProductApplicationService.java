package com.netfoodz.store.catalog.application;

import com.netfoodz.store.catalog.domain.model.Product;
import com.netfoodz.store.catalog.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductApplicationService {

	private ProductRepository productRepository;

	@Autowired
	public ProductApplicationService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}


	public List<ProductDTO> findAllInCatalog() {
		List<Product> products = productRepository.findInCatalog();

		List<ProductDTO> dtos = new ArrayList<>();

		for (Product product : products) {
			ProductDTO dto = new ProductDTO();
			dto.id = product.getId().getId();
			dto.name = product.getName();
			dto.description = product.getDescription();
			dto.price = product.getPrice().doubleValue();

			dtos.add(dto);
		}

		return dtos;
	}
}
