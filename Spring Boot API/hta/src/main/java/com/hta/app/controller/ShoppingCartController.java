package com.hta.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hta.app.DTO.ShoppingCartDTO;
import com.hta.app.model.Product;
import com.hta.app.model.ShoppingCart;
import com.hta.app.model.User;
import com.hta.app.repository.UserRepository;
import com.hta.app.service.ShoppingCartService;


@CrossOrigin(origins = "*")
@RestController //@Controller y @ResponseBody
@RequestMapping("/api/v1/shoppingCart")
public class ShoppingCartController {
	
	    ShoppingCartService shoppingCartService;

		private final UserRepository userRepository;
		
		public ShoppingCartController(ShoppingCartService shoppingCartService, UserRepository userRepository) {
			super();
			this.shoppingCartService = shoppingCartService;
			this.userRepository = userRepository;
		}
		
		
		@GetMapping("/{id}")
		ShoppingCart getShoppingCartById(@PathVariable("id")Long id) {
			ShoppingCart existingShoppingCart = shoppingCartService.findById(id);
			return existingShoppingCart;
		}
		
		
		
		
		@PutMapping("/{id}")
		ShoppingCart updateShoppingCart(@PathVariable("id") Long id, @RequestBody ShoppingCart shoppingCart) {
			ShoppingCart updatedShoppingCart = shoppingCartService.update(id, shoppingCart);
			return updatedShoppingCart;
			}
		
		@DeleteMapping("/{id}")
		ResponseEntity<Void> deleteShoppingCart(@PathVariable("id") Long id) {
		shoppingCartService.deleteById(id);
		return ResponseEntity.noContent().build();
		}
		
		@PostMapping
		ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCartDTO dto) {
		    User user = userRepository.findById(dto.getUserId())
		        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getUserId()));

		    ShoppingCart shoppingCart = new ShoppingCart();
		    shoppingCart.setSubtotal(dto.getSubtotal());
		    shoppingCart.setShipment(dto.getShipment());
		    shoppingCart.setTotal(dto.getTotal());
		    shoppingCart.setUser(user);
		    
		    ShoppingCart newShoppingCart = shoppingCartService.save(shoppingCart);
		    return new ResponseEntity<>(newShoppingCart, HttpStatus.CREATED);
		}

}
