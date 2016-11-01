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
	private StringProperty prise;
	
	public ArticleViewModel(){
		//NOP
	}
	
	public ArticleViewModel(StringProperty articleNumber2, StringProperty name2, StringProperty description2, StringProperty rechnungId2, StringProperty category2, StringProperty prise2) {
		this.articleNumber = articleNumber2;
		this.name = name2;
		this.description = description2;
		this.rechnungId = rechnungId2;
		this.category = category2;
		this.prise = prise2;
	}
	public String getArticleNumber() {
		return articleNumber.get();
	}
	public void setArticleNumber(StringProperty articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getName() {
		return name.get();
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public String getDescription() {
		return description.get();
	}
	public void setDescription(StringProperty description) {
		this.description = description;
	}
	public String getRechnungId() {
		return rechnungId.get();
	}
	public void setRechnungId(StringProperty rechnungId) {
		this.rechnungId = rechnungId;
	}
	public String getCategory() {
		return category.get();
	}
	public void setCategory(StringProperty category) {
		this.category = category;
	}
	public String getPrise() {
		return prise.get();
	}
	public void setPrise(StringProperty prise) {
		this.prise = prise;
	}
	
}
