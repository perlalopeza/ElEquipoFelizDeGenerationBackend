package com.hta.app.controller;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hta.app.model.CartItem;
import com.hta.app.model.CartItemId;
import com.hta.app.model.Product;
import com.hta.app.service.CartItemService;

public class CartItemController {
	
	CartItemService cartItemService ;

	public CartItemController(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	@GetMapping
	ResponseEntity<Iterable<CartItem>>getAllCartItem(){
		Iterable<CartItem> cartItem= cartItemService.findAll();
		return ResponseEntity.ok(cartItem);

		}
	
	 @GetMapping("/{productId}/{shoppingCartId}")
	     ResponseEntity<CartItem> getCartItemById(
	            @PathVariable Long productId,
	            @PathVariable Long shoppingCartId) {

	        CartItemId id = new CartItemId(productId, shoppingCartId);
	        CartItem item = cartItemService.findById(id).orElse(null);
	        if (item != null) {
	            return ResponseEntity.ok(item);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 @PostMapping
	    ResponseEntity<CartItem> createCartItem(@RequestBody CartItem item) {
	        CartItem itemGuardado = cartItemService.save(item);
	        return new ResponseEntity<>(itemGuardado, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/{productId}/{shoppingCartId}")
	    ResponseEntity<Void> deleteCartItem(
	            @PathVariable Long productId,
	            @PathVariable Long shoppingCartId) {

	        CartItemId id = new CartItemId(productId, shoppingCartId);
	        cartItemService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	
	
}
