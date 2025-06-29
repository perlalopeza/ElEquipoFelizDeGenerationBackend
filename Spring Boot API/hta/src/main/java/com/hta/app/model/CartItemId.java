package com.hta.app.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;



@Embeddable 
public class CartItemId implements Serializable {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long product;
		private Long shoppingCart;
		
		
		public CartItemId() {
			
		}


		public CartItemId(Long product, Long shoppingCart) {
			super();
			this.product = product;
			this.shoppingCart = shoppingCart;
		}


		@Override
		public int hashCode() {
			return Objects.hash(product, shoppingCart);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CartItemId other = (CartItemId) obj;
			return Objects.equals(product, other.product) && Objects.equals(shoppingCart, other.shoppingCart);
		}

		
}
