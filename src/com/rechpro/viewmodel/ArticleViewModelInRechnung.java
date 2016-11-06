package com.rechpro.viewmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kdogan
 *
 */

public class ArticleViewModelInRechnung {

	private int id;
	private String name;
	private int articleNumber;
	private String description;
	private int rechnungId;
	private int category;
	private Double prise;
	private int number = 0;
	
	public ArticleViewModelInRechnung(){
		//NOP
	}
	public ArticleViewModelInRechnung(int articleNumber, String name, String description, int rechnungId, int category, double prise) {
		this.articleNumber = articleNumber;
		this.name = name;
		this.description = description;
		this.rechnungId = rechnungId;
		this.category = category;
		this.prise = prise;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRechnungId() {
		return rechnungId;
	}

	public void setRechnung_id(int rechnung_id) {
		this.rechnungId = rechnung_id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Double getPrise() {
		return prise;
	}

	public void setPrise(Double prise) {
		this.prise = prise;
	}

	public int getId() {
		return id;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}
	
	public double getTotalPrise(){
		return this.number * this.prise;
	}
}
