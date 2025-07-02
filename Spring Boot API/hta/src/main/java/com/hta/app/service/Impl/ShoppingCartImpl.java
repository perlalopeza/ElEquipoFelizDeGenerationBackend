package com.hta.app.service.Impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hta.app.model.ShoppingCart;
import com.hta.app.repository.ShoppingCartRepository;
import com.hta.app.service.ShoppingCartService;

@Service
public class ShoppingCartImpl implements ShoppingCartService{
	
	ShoppingCartRepository shoppingCartRepository;

	public ShoppingCartImpl(ShoppingCartRepository shoppingCartRepository) {
		this.shoppingCartRepository = shoppingCartRepository;
	}
	
	@Override
	public Iterable<ShoppingCart> findAll() {
	  
		return shoppingCartRepository.findAll();
	}
	
	@Override
	public ShoppingCart findById(Long id) {
		
			Optional<ShoppingCart> shoppingCartOpt = shoppingCartRepository.findById(id);
			if(shoppingCartOpt.isEmpty()) { 
				throw new IllegalStateException("ShoppingCart with id: "+ id +" does not exist");
			}
			ShoppingCart existingShoppingCart = shoppingCartOpt.get();
			return existingShoppingCart;
		};
		
		@Override
		public ShoppingCart save(ShoppingCart shoppingCart) {
			shoppingCart.setId(null); 
			ShoppingCart newShoppingCart = shoppingCartRepository.save(shoppingCart);
			
			return newShoppingCart;
		}
		
		@Override
		public ShoppingCart update(Long id, ShoppingCart shoppingCart) {
			ShoppingCart existingShoppingCart = findById(id);
			
			existingShoppingCart.setSubtotal(shoppingCart.getSubtotal());
			existingShoppingCart.setShipment(shoppingCart.getShipment());
			existingShoppingCart.setTotal(shoppingCart.getTotal());
			
			ShoppingCart updatedShoppingCart = shoppingCartRepository.save(existingShoppingCart);
			return updatedShoppingCart;

  }

		@Override
		public void deleteById(Long id) {
			ShoppingCart existingShoppingCart = findById(id);
			shoppingCartRepository.delete(existingShoppingCart);
		}

		@Override
		public Set<String> getUsersWithSpecificShoppingCartId(Long id) {
			return null;
		}
		
}
