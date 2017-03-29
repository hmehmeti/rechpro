package com.rechpro.persistence;

import java.util.List;

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
	public Category retrieveCategory(String name) {
		return this.categoryDao.findCategoryByName(name);
	}

	@Transactional
	public Category createCategory(Category category) {
		return this.categoryDao.persistOrMerge(category);
	}

	@Transactional
	public List<Category> retrieveAllCategories() {
		return this.categoryDao.retrieveAllCategories();
	}
}
