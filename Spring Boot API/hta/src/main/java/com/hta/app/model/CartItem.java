package com.hta.app.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {
	
	@EmbeddedId
	private CartItemId id;


	@ManyToOne
	@MapsId("product")
	@JoinColumn(name = "products_id", nullable = false)
	private Product product;

    @ManyToOne
    @MapsId("shoppingCart")
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

	@Column(nullable=false)
    private int quantity;
    @Column(name ="price_at_purchase", precision = 10, scale = 2, nullable=false)
    private BigDecimal priceAtPurchase;
    
    
    public CartItem() {
    	
    }


	public CartItem(Product product, ShoppingCart shoppingCart, Integer quantity, BigDecimal priceAtPurchase) {
		super();
		this.product = product;
		this.shoppingCart = shoppingCart;
		this.quantity = quantity;
		this.priceAtPurchase = priceAtPurchase;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}


	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getPriceAtPurchase() {
		return priceAtPurchase;
	}


	public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}


	@Override
	public String toString() {
		return "CartItem [product=" + product + ", quantity=" + quantity + ", priceAtPurchase=" + priceAtPurchase + "]";
	}
    
    
    
    
    
}
