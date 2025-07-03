package com.hta.app.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartItemId implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long product;
    private Long shoppingCart;

    public CartItemId() {}

    public CartItemId(Long product, Long shoppingCart) {
        this.product = product;
        this.shoppingCart = shoppingCart;
    }

    // Getters y setters
    public Long getProduct() { return product; }
    public void setProduct(Long product) { this.product = product; }

    public Long getShoppingCart() { return shoppingCart; }
    public void setShoppingCart(Long shoppingCart) { this.shoppingCart = shoppingCart; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemId)) return false;
        CartItemId that = (CartItemId) o;
        return Objects.equals(product, that.product) &&
               Objects.equals(shoppingCart, that.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, shoppingCart);
    }
}
