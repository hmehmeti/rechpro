package com.rechpro.persistence.dao;

import java.util.List;

import com.rechpro.entity.Category;

public interface ICategoryDAO {

	public Category findCategoryByName(String name);
	public Category persistOrMerge(Category category);
	public List<Category> retrieveAllCategories();
}
