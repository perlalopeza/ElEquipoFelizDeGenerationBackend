package com.hta.app.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hta.app.model.CartItem;
import com.hta.app.model.CartItemId;
import com.hta.app.repository.CartItemRepository;
import com.hta.app.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	 @Autowired
	    private CartItemRepository repository;

	    @Override
	    public Iterable<CartItem> findAll() {
	        return repository.findAll();
	    }

	    @Override
	    public Optional<CartItem> findById(CartItemId id) {
	        return repository.findById(id);
	    }

	    @Override
	    public CartItem save(CartItem item) {
	        return repository.save(item);
	    }

	    @Override
	    public void delete(CartItemId id) {
	        repository.deleteById(id);
	    }
	
}
