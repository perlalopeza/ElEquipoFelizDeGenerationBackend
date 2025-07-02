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

import com.hta.app.DTO.ProductDTO;
import com.hta.app.model.Category;
import com.hta.app.model.Product;
import com.hta.app.repository.CategoryRepository;
import com.hta.app.service.ProductService;

@CrossOrigin(origins = "*")
@RestController //@Controller y @ResponseBody
@RequestMapping("/api/v1/products")
public class ProductController {
	ProductService productService;
	private final CategoryRepository categoryRepository;
	public ProductController(ProductService productService, CategoryRepository categoryRepository) {
	    this.productService = productService;
	    this.categoryRepository = categoryRepository;
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
	ResponseEntity<Product> createProduct(@RequestBody ProductDTO dto) {
	    Category category = categoryRepository.findById(dto.getCategoryId())
	        .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + dto.getCategoryId()));

	    Product product = new Product();
	    product.setProductName(dto.getProductName());
	    product.setPrice(dto.getPrice());
	    product.setDescription(dto.getDescription());
	    product.setStock(dto.getStock());
	    product.setImage(dto.getImage());
	    product.setDiscount(dto.getDiscount());
	    product.setCategory(category);  

	    Product newProduct = productService.save(product);
	    return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}
}
