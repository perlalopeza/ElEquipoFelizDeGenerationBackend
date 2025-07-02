package com.hta.app.service;

import java.util.Set;

import com.hta.app.model.ShoppingCart;

public interface ShoppingCartService {
	
	Iterable<ShoppingCart> findAll();
	ShoppingCart findById(Long id);
	ShoppingCart save(ShoppingCart shoppingCart);
	ShoppingCart update(Long id, ShoppingCart shoppingCart);
	void deleteById(Long id);
	Set<String> getUsersWithSpecificShoppingCartId(Long id);

}
