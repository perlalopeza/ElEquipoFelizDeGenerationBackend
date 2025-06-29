package com.hta.app.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="shopping_cart")
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(precision = 10, scale = 2, nullable=false)
	private BigDecimal subtotal;
	@Column(precision = 10, scale = 2, nullable=false)
	private BigDecimal shipment;
	@Column(precision = 10, scale = 2, nullable=false)
	private BigDecimal total;
	 @ManyToOne 
		@JoinColumn(name="users_id", nullable=false) 
	    private User user;
	    
	    
	    public ShoppingCart() {
	    	
	    }


		public ShoppingCart(Long id, BigDecimal subtotal, BigDecimal shipment, BigDecimal total, User user) {
			super();
			this.id = id;
			this.subtotal = subtotal;
			this.shipment = shipment;
			this.total = total;
			this.user = user;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public BigDecimal getSubtotal() {
			return subtotal;
		}


		public void setSubtotal(BigDecimal subtotal) {
			this.subtotal = subtotal;
		}


		public BigDecimal getShipment() {
			return shipment;
		}


		public void setShipment(BigDecimal shipment) {
			this.shipment = shipment;
		}


		public BigDecimal getTotal() {
			return total;
		}


		public void setTotal(BigDecimal total) {
			this.total = total;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ShoppingCart [id=");
			builder.append(id);
			builder.append(", subtotal=");
			builder.append(subtotal);
			builder.append(", shipment=");
			builder.append(shipment);
			builder.append(", total=");
			builder.append(total);
			builder.append("]");
			return builder.toString();
		}
	    
	    
}
