package com.hta.app.service.Impl;

import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.hta.app.model.Category;
import com.hta.app.repository.CategoryRepository;
import com.hta.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Iterable<Category> findAll() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Long id) {
		Optional<Category> categoryOpt = categoryRepository.findById(id);
		if(categoryOpt.isEmpty()) { 
			throw new IllegalStateException("Category with id: "+ id +" does not exist");
		}
		Category existingCategory = categoryOpt.get();
		return existingCategory;
	}

	@Override
	public Category save(Category category) {
		category.setId(null); 
		Category newCategory = categoryRepository.save(category);
		
		return newCategory;
	}

	@Override
	public Category update(Long id, Category category) {
Category existingCategory = findById(id);
		
		existingCategory.setName(category.getName());
		Category updatedCategory = categoryRepository.save(existingCategory);
		return updatedCategory;
	}

	@Override
	public void deleteById(Long id) {
		Category existingCategory = findById(id);
		categoryRepository.delete(existingCategory);
		
	}

	@Override
	public Set<String> getUsersWithSpecificCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
