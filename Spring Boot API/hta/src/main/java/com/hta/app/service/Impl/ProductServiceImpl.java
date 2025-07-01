package com.hta.app.service.Impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hta.app.model.Product;
import com.hta.app.repository.ProductRepository;
import com.hta.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	ProductRepository  productRepository;
     
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Iterable<Product> findAll() {
	  
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {
		
			Optional<Product> productOpt = productRepository.findById(id);
			if(productOpt.isEmpty()) { 
				throw new IllegalStateException("Product with id: "+ id +" does not exist");
			}
			Product existingProduct = productOpt.get();
			return existingProduct;
		};
	

	@Override
	public Product save(Product product) {
		product.setId(null); 
		Product newProduct = productRepository.save(product);
		
		return newProduct;
	}

	@Override
	public Product update(Long id, Product product) {
		Product existingProduct = findById(id);
		
		existingProduct.setProductName(product.getProductName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setDiscount(product.getDiscount());
		existingProduct.setImage(product.getImage());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setStock(product.getStock());
		Product updatedProduct = productRepository.save(existingProduct);
		return updatedProduct;
	}

	@Override
	public void deleteById(Long id) {
		Product existingProduct = findById(id);
		productRepository.delete(existingProduct);
		
	}

	@Override
	public Set<String> getUsersWithSpecificProductId(Long id) {
		return null;
	}

}
