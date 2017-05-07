package com.rechpro.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rechpro.entity.Category;
import com.rechpro.viewmodel.CategoryViewModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author eosmanlliu
 *
 */
public class CategoryTransformer {

	public Category entityFromParameterList(Map<Enum, String> categoryParameterList) {
		return new Category(categoryParameterList);
	}

	public List<CategoryViewModel> convertAndGetAllCategories(List<Category> categories) {
		List<CategoryViewModel> catViewModelList = new ArrayList<>();
		for (Category category : categories) {
			catViewModelList.add(entityToViewModel(category));
		}
		return catViewModelList;
	}

	private CategoryViewModel entityToViewModel(Category category) {
		CategoryViewModel catViewModel = new CategoryViewModel();
		SimpleStringProperty categoryName = new SimpleStringProperty(category.getName());
		SimpleStringProperty categoryDescription = new SimpleStringProperty(category.getDescription());
		
		catViewModel.setCategoryName(categoryName);
		catViewModel.setDescription(categoryDescription);
		return catViewModel;
	}

	
	
}
