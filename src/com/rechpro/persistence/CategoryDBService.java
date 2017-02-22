package com.rechpro.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import com.rechpro.entity.Category;

public class CategoryDBService {

	private DBService<Category> dbService;
	
	public CategoryDBService() {
		dbService = new DBService<>(Category.class.getName());
	}
	
	public Category getCategoryForName(String name) throws NoSuchElementException {
		List<Category> categories = dbService.getEntities();
		Category category = categories.stream()
				.filter(categoryObj -> categoryObj.getName().equals(name))
				.findFirst()
				.get();
		return category;
	}
}
