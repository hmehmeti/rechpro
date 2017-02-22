package com.rechpro.viewmodel;

import javafx.beans.property.StringProperty;

/**
 * @author Kamuran Dogan
 *
 */

public class ArticleViewModel {

	private StringProperty articleNumber;
	private StringProperty name;
	private StringProperty description;
	private StringProperty rechnungId;
	private StringProperty categoryName;
	private StringProperty price;
	
	public ArticleViewModel(){
		//NOP
	}
	
	public ArticleViewModel(StringProperty articleNumber, StringProperty name, StringProperty description, StringProperty rechnungId, StringProperty categoryName, StringProperty price) {
		this.articleNumber = articleNumber;
		this.name = name;
		this.description = description;
		this.rechnungId = rechnungId;
		this.categoryName = categoryName;
		this.price = price;
	}
	public StringProperty getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(StringProperty articleNumber) {
		this.articleNumber = articleNumber;
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public StringProperty getDescription() {
		return description;
	}
	public void setDescription(StringProperty description) {
		this.description = description;
	}
	public StringProperty getRechnungId() {
		return rechnungId;
	}
	public void setRechnungId(StringProperty rechnungId) {
		this.rechnungId = rechnungId;
	}
	public StringProperty getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(StringProperty categoryName) {
		this.categoryName = categoryName;
	}
	public StringProperty getPrice() {
		return price;
	}
	public void setPrice(StringProperty price) {
		this.price = price;
	}
	
}
