package com.hta.app.service;

import java.util.Set;

import com.hta.app.model.Category;

public interface CategoryService {

	Iterable<Category> findAll();
	Category findById(Long id);
	Category save(Category category);
	Category update(Long id, Category category);
	void deleteById(Long id);
	Set<String> getUsersWithSpecificCategoryId(Long id);

}
