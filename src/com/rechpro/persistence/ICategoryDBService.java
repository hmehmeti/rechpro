package com.rechpro.persistence;

import java.util.List;

import com.rechpro.entity.Category;

public interface ICategoryDBService {

	public Category createCategory(Category category);
	public Category retrieveCategory(String name);
	public List<Category> retrieveAllCategories();
	public List<String> retrieveAllCategorieNames();
	
}
