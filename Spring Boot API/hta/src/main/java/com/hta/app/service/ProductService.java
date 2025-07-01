package com.hta.app.service;

import java.util.Set;

import com.hta.app.model.Product;

public interface ProductService {

	Iterable<Product> findAll();
	Product findById(Long id);
	Product save(Product product);
	Product update(Long id, Product product);
	void deleteById(Long id);
	Set<String> getUsersWithSpecificProductId(Long id);

}
