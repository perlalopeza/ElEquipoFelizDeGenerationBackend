package com.hta.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hta.app.model.Product;
import com.hta.app.service.ProductService;

public class ProductController {
	ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping
	ResponseEntity<Iterable<Product>>getAllProduct(){
		Iterable<Product> products= productService.findAll();
		return ResponseEntity.ok(products);

		}
	
	@PutMapping("/{id}")
	Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		Product updatedProduct = productService.update(id, product);
		return updatedProduct;
		}

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
	productService.deleteById(id);
	return ResponseEntity.noContent().build();
	}

	@PostMapping
	ResponseEntity<Product> createProduct(@RequestBody Product product) {
	Product newProduct = productService.save(product);
	return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}
}
