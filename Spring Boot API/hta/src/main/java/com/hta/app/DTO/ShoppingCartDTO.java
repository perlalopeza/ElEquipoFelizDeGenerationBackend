package com.hta.app.DTO;

import java.math.BigDecimal;

public class ShoppingCartDTO {
	
	private BigDecimal subtotal;
	private BigDecimal shipment;
	private BigDecimal total;
	private Long userId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
