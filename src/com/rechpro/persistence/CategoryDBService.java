package com.rechpro.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rechpro.entity.Category;
import com.rechpro.persistence.dao.hibernate.CategoryDAO;

@Service
public class CategoryDBService implements ICategoryDBService{

	@Autowired(required=true)
	private CategoryDAO categoryDao;

	@Transactional
	@Override
	public Category retrieveCategory(String name) {
		return this.categoryDao.findCategoryByName(name);
	}

	@Transactional
	@Override
	public Category createCategory(Category category) {
		return this.categoryDao.persistOrMerge(category);
	}

	@Transactional
	@Override
	public List<Category> retrieveAllCategories() {
		return this.categoryDao.findAllCategories();
	}

	@Transactional
	@Override
	public List<String> retrieveAllCategorieNames() {
		List<Category> categories = retrieveAllCategories();
		List<String> categoryNames = categories.stream()
										.map(Category::getName)
										.collect(Collectors.toList());
		return categoryNames;
	}
}
