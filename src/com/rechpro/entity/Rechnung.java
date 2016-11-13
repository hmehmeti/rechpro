package com.rechpro.entity;

import java.sql.Date;
import java.util.List;

/**
 * @author hmehmeti
 * Rechnung Object for DBPersistence
 *
 */
public class Rechnung {

	private long id;
	private Customer customer;
	private Date date;
	private Double amount;
	private Double tax;
	private Float taxRate;
	private List<Article> items;


	public Rechnung(long id) {

		this.id = id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Double getTax() {
		return tax;
	}


	public void setTax(Double tax) {
		this.tax = tax;
	}


	public Float getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
	}


	public List<Article> getItems() {
		return items;
	}


	public void addItam(Article item) {
		this.items.add(item);
	}

	public void addItams(List<Article> items) {
		this.items.addAll(items);
	}
	public void removeItam(Article item) {
		if(this.items.contains(item))
			this.items.remove(item);
	}

	public long getId() {
		return id;
	}

}
