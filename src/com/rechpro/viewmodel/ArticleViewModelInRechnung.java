package com.rechpro.viewmodel;

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
	private int number = 1;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleNumber;
		result = prime * result + category;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + ((prise == null) ? 0 : prise.hashCode());
		result = prime * result + rechnungId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleViewModelInRechnung other = (ArticleViewModelInRechnung) obj;
		if (articleNumber != other.articleNumber)
			return false;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (prise == null) {
			if (other.prise != null)
				return false;
		} else if (!prise.equals(other.prise))
			return false;
		if (rechnungId != other.rechnungId)
			return false;
		return true;
	}
}
