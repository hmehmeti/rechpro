package com.rechpro.persistence;

import java.util.List;

import com.rechpro.entity.Category;

public interface ICategoryDBService {

	public Category retrieveCategory(String name);
	public Category createCategory(Category category);
	public List<Category> retrieveAllCategories();
	
}
