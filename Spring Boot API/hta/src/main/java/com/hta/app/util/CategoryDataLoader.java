package com.hta.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

import com.hta.app.model.Category;
import com.hta.app.repository.CategoryRepository;


@Component
@Order(1)
public class CategoryDataLoader implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		categoryRepository.save(new Category(null, "Productos para invernadero"));
		categoryRepository.save(new Category(null, "Mallas sombra"));
		categoryRepository.save(new Category(null, "Mallas decorativas"));
		categoryRepository.save(new Category(null, "Accesorios de hidroponía"));
	}
	
	
}
