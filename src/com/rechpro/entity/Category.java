package com.rechpro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author eosmanlliu
 *
 */
@Entity
@Table(name="category")
public class Category {

	public Category(){
		//MUST have a default constructor to initialize Entity
	}
	
	public Category(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="name")
	private String name;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(String categoryName) {
		name = categoryName;
	}

	public String getName() {
		return name;
	}

	@Column(name="description")
	private String description;
}
