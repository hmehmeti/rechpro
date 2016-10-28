package com.rechpro.entity;

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
@Entity
@Table(name="article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="article_number")
	private int articleNumber;
	
	@Column(name="description")
	private String description;
	
	@Column(name="rechung_id")
	private int rechnungId;
	
	@Column(name="category")
	private int category;
	
	@Column(name="prise")
	private Double prise;

	public Article(int articleNumber2, String name2, String description2, int rechnungId2, int category2, double prise2) {
		this.articleNumber = articleNumber2;
		this.name = name2;
		this.description = description2;
		this.rechnungId = rechnungId2;
		this.category = category2;
		this.prise = prise2;
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
}
