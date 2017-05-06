package com.rechpro.ui;

import javafx.beans.property.StringProperty;

/**
 * 
 * @author eosamnlliu
 *
 */
public class CategoryViewModel {

	private StringProperty categoryName;
	private StringProperty description;
	
	public CategoryViewModel() {
		// NOP
	}

	public CategoryViewModel(StringProperty categoryName, StringProperty description) {
		this.categoryName = categoryName;
		this.description = description;
	}

	public StringProperty getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(StringProperty categoryName) {
		this.categoryName = categoryName;
	}

	public StringProperty getDescription() {
		return description;
	}

	public void setDescription(StringProperty description) {
		this.description = description;
	}
}
