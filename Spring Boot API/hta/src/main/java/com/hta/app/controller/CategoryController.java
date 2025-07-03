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

import com.hta.app.model.Category;
import com.hta.app.model.User;
import com.hta.app.service.CategoryService;
@CrossOrigin(origins = "*")
@RestController //@Controller y @ResponseBody
@RequestMapping("/api/v1/categories")
public class CategoryController {

	CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping
	ResponseEntity<Iterable<Category>>getAllCategories(){
		Iterable<Category> categories= categoryService.findAll();
		return ResponseEntity.ok(categories);

		}
	
	@GetMapping("/{id}")
	Category getCategoryById(@PathVariable("id")Long id) {
		Category existingCategory = categoryService.findById(id);
		return existingCategory;
	}
	
	
	
	@PutMapping("/{id}")
	Category updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
		Category updatedCategory = categoryService.update(id, category);
		return updatedCategory;
		}

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
	categoryService.deleteById(id);
	return ResponseEntity.noContent().build();
	}

	@PostMapping
	ResponseEntity<Category> createCategory(@RequestBody Category category) {
	Category newCategory = categoryService.save(category);
	return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
	}


}
