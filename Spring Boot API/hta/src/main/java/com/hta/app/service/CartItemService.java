package com.hta.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.hta.app.model.CartItem;
import com.hta.app.model.CartItemId;
import com.hta.app.repository.CartItemRepository;

public interface CartItemService {


	    Iterable<CartItem> findAll();
	    Optional<CartItem> findById(CartItemId id);
	    CartItem save(CartItem item);
	    void delete(CartItemId id);
	    
}


