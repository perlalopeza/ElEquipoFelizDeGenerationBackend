package com.hta.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.hta.app.model.CartItem;
import com.hta.app.model.CartItemId;

public interface CartItemRepository extends CrudRepository<CartItem, CartItemId> {

}
