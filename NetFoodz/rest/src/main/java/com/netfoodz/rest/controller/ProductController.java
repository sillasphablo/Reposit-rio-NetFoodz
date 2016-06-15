package com.netfoodz.rest.controller;

import com.netfoodz.store.catalog.application.ProductApplicationService;
import com.netfoodz.store.catalog.application.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductApplicationService productApplicationService;

	@Autowired
	public ProductController(ProductApplicationService productApplicationService) {
		this.productApplicationService = productApplicationService;
	}



	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getProducts() {

		List<ProductDTO> products = productApplicationService.findAllInCatalog();

		if (products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.ok().body(products);
		}
	}
}
