package com.hta.app.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name= "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name= "product_name", nullable=false)
	private String productName;
	@Column(precision = 10, scale = 2, nullable=false)
	private BigDecimal price;
	@Column(columnDefinition = "TEXT", nullable=false)
	private String description;
	@Column(nullable=false)
	private Long stock;
	@Column(columnDefinition = "TEXT", nullable=false)
	private String image;
	@Column(precision = 10, scale = 2, nullable=false)
	private BigDecimal discount;
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	
	
	
	
	public Product() {
		
	}




	public Product(Long id, String productName, BigDecimal price, String description, Long stock, String image,
			BigDecimal discount, Category category) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.image = image;
		this.discount = discount;
		this.category = category;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getProductName() {
		return productName;
	}




	public void setProductName(String productName) {
		this.productName = productName;
	}




	public BigDecimal getPrice() {
		return price;
	}




	public void setPrice(BigDecimal price) {
		this.price = price;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Long getStock() {
		return stock;
	}




	public void setStock(Long stock) {
		this.stock = stock;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public BigDecimal getDiscount() {
		return discount;
	}




	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}




	public Category getCategory() {
		return category;
	}




	public void setCategory(Category category) {
		this.category = category;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", price=");
		builder.append(price);
		builder.append(", description=");
		builder.append(description);
		builder.append(", stock=");
		builder.append(stock);
		builder.append(", image=");
		builder.append(image);
		builder.append(", discount=");
		builder.append(discount);
		builder.append("]");
		return builder.toString();
	}




	



	

	
	
}
