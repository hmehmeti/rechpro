package com.rechpro.entity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.ApplicationContext;

import com.rechpro.appcontext.ApplicationContextProvider;
import com.rechpro.persistence.ICategoryDBService;
import com.rechpro.worker.ArticleParameters;

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
	
	@Column(name="rechnung_id")
	private int rechnungId;
	
	@Column(name="price")
	private Double price;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id", referencedColumnName="id")
	private Category category;

	public Article(){
		//MUST have a default constructor to initialise Entity
	}
	public Article(int articleNumber, String name, String description, int rechnungId, double price) {
		this.articleNumber = articleNumber;
		this.name = name;
		this.description = description;
		this.rechnungId = rechnungId;
		this.price = price;
	}

	public Article(Map<Enum, String> articleParameterList) {
		this.name = articleParameterList.get(ArticleParameters.NAME);
		this.articleNumber = Integer.valueOf(articleParameterList.get(ArticleParameters.ARTICLENUMBER));
		this.price = Double.valueOf(articleParameterList.get(ArticleParameters.PRICE));
		this.description = articleParameterList.get(ArticleParameters.DESCRIPTION);
		String categoryName = articleParameterList.get(ArticleParameters.CATEGORY);
		ICategoryDBService dbService = getCategoryDBServiceBean();
		this.category = dbService.retrieveCategory(categoryName);
	}
	
	private ICategoryDBService getCategoryDBServiceBean() {
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		ICategoryDBService dbService = (ICategoryDBService) context.getBean("categoryDBService");
		return dbService;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
}
