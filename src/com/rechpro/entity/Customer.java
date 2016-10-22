package com.rechpro.entity;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rechpro.worker.UserParameters;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * @author eosmanlliu
 *
 */

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="street")
	private String street;
	
	@Column(name="home_no")
	private String no;
	
	@Column(name="post_code")
	private String postCode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="mobile_phone")
	private String mobilePhone;
	
	@Column(name="fax")
	private String fax;
	
	@Column(name="email")
	private String email;
	
	@Column(name="birth_day")
	private String birthDay;
	
	@Column(name="bank_no")
	private String bankNo;
	
	@Column(name="blz")
	private String blz;
	
	@Column(name="iban")
	private String iban;
	
	@Column(name="bic_no")
	private String bicNo;
	

	public Customer() {
	}
	
	public Customer(HashMap<Enum, String> customParameterList) {
		this.sex = customParameterList.get(UserParameters.SEX);
		this.firstName = customParameterList.get(UserParameters.FIRSTNAME);
		this.lastName = customParameterList.get(UserParameters.LASTNAME);
		this.street = customParameterList.get(UserParameters.STREET);
		this.no = customParameterList.get(UserParameters.NO);
		this.postCode = customParameterList.get(UserParameters.POSTCODE);
		this.city = customParameterList.get(UserParameters.CITY);
		this.country = customParameterList.get(UserParameters.COUNTRY);
		this.phone = customParameterList.get(UserParameters.PHONE);
		this.mobilePhone = customParameterList.get(UserParameters.MOBILE_PHONE);
		this.fax = customParameterList.get(UserParameters.FAX);
		this.email = customParameterList.get(UserParameters.EMAIL);
		this.birthDay = customParameterList.get(UserParameters.BIRTHDAY);
		this.bankNo = customParameterList.get(UserParameters.BANK_NO);
		this.blz = customParameterList.get(UserParameters.BLZ);
		this.iban = customParameterList.get(UserParameters.IBAN);
		this.bicNo = customParameterList.get(UserParameters.BIC_NO);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBlz() {
		return blz;
	}

	public void setBlz(String blz) {
		this.blz = blz;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBicNo() {
		return bicNo;
	}

	public void setBicNo(String bicNo) {
		this.bicNo = bicNo;
	}

}
