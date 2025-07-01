package com.hta.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.hta.app.model.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {

}
