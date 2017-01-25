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
	private StringProperty category;
	private StringProperty price;
	
	public ArticleViewModel(){
		//NOP
	}
	
	public ArticleViewModel(StringProperty articleNumber, StringProperty name, StringProperty description, StringProperty rechnungId, StringProperty category, StringProperty price) {
		this.articleNumber = articleNumber;
		this.name = name;
		this.description = description;
		this.rechnungId = rechnungId;
		this.category = category;
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
	public StringProperty getCategory() {
		return category;
	}
	public void setCategory(StringProperty category) {
		this.category = category;
	}
	public StringProperty getPrice() {
		return price;
	}
	public void setPrice(StringProperty price) {
		this.price = price;
	}
	
}
