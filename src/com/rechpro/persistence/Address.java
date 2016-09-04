package com.rechpro.persistence;

import javafx.beans.property.SimpleStringProperty;

public class Address {

	private final SimpleStringProperty street;
	private final SimpleStringProperty number;
	private final SimpleStringProperty postCode;
	private final SimpleStringProperty city;
	private final SimpleStringProperty country;

	public Address(String street, String number, String postCode, String city, String country){
		this.street = new SimpleStringProperty(street);
		this.number = new SimpleStringProperty(number);
		this.postCode = new SimpleStringProperty(postCode);
		this.city = new SimpleStringProperty(city);
		this.country = new SimpleStringProperty(country);
	}

	public SimpleStringProperty getStreet() {
		return street;
	}

	public SimpleStringProperty getNumber() {
		return number;
	}

	public SimpleStringProperty getPostCode() {
		return postCode;
	}

	public SimpleStringProperty getCity() {
		return city;
	}

	public SimpleStringProperty getCountry() {
		return country;
	}

	public String toString (){
		return getStreet().getValue()+getNumber().getValue()+getPostCode().getValue()+getCity().getValue()+getCountry().getValue();
	}
}
