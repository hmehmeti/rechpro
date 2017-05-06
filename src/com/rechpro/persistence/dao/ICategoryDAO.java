package com.rechpro.persistence.dao;

import java.util.List;

import com.rechpro.entity.Category;

public interface ICategoryDAO {

	public Category persistOrMerge(Category category);
	public Category findCategoryByName(String name);
	public List<Category> findAllCategories();
}
