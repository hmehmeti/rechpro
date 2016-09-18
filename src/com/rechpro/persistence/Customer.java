package com.rechpro.persistence;

import java.util.HashMap;

import com.rechpro.worker.UserParameters;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author hmehmeti
 * Kunden Object for DBPersistence
 *
 */
	public class Customer {
		private StringProperty id;
        private StringProperty sex;
        private StringProperty firstName;
    	private StringProperty lastName;
    	private StringProperty street;
    	private StringProperty no;
    	private StringProperty postCode;
    	private StringProperty city;
    	private StringProperty country;
    	private StringProperty phone;
    	private StringProperty mobilePhone;
    	private StringProperty fax;
    	private StringProperty email;
    	private StringProperty birthDay;
    	private StringProperty bankNo;
    	private StringProperty blz;
    	private StringProperty iban;
    	private StringProperty bicNo;

        public Customer(HashMap<Enum, String> customParameterList) {
        	this.id = new SimpleStringProperty(customParameterList.get(UserParameters.ID));;
        	this.sex = new SimpleStringProperty(customParameterList.get(UserParameters.SEX));
            this.firstName =  new SimpleStringProperty(customParameterList.get(UserParameters.FIRSTNAME));
        	this.lastName =  new SimpleStringProperty(customParameterList.get(UserParameters.LASTNAME));
        	this.street =  new SimpleStringProperty(customParameterList.get(UserParameters.STREET));
        	this.no =  new SimpleStringProperty(customParameterList.get(UserParameters.NO));
        	this.postCode =  new SimpleStringProperty(customParameterList.get(UserParameters.POSTCODE));
        	this.city =  new SimpleStringProperty(customParameterList.get(UserParameters.CITY));
        	this.country =  new SimpleStringProperty(customParameterList.get(UserParameters.COUNTRY));
        	this.phone =  new SimpleStringProperty(customParameterList.get(UserParameters.PHONE));
        	this.mobilePhone =  new SimpleStringProperty(customParameterList.get(UserParameters.MOBILE_PHONE));
        	this.fax =  new SimpleStringProperty(customParameterList.get(UserParameters.FAX));
        	this.email =  new SimpleStringProperty(customParameterList.get(UserParameters.EMAIL));
        	this.birthDay =  new SimpleStringProperty(customParameterList.get(UserParameters.BIRTHDAY));
        	this.bankNo =  new SimpleStringProperty(customParameterList.get(UserParameters.BANK_NO));
        	this.blz =  new SimpleStringProperty(customParameterList.get(UserParameters.BLZ));
        	this.iban =  new SimpleStringProperty(customParameterList.get(UserParameters.IBAN));
        	this.bicNo =  new SimpleStringProperty(customParameterList.get(UserParameters.BIC_NO));
        }

        public StringProperty getId() {
			return id;
		}
        
        public StringProperty getSex() {
			return sex;
		}
        
        public void setSex(StringProperty sex) {
			this.sex = sex;
		}

		public StringProperty getFirstName() {
			return firstName;
		}
		
		public void setFirstName(StringProperty firstName) {
			this.firstName = firstName;
		}

		public StringProperty getLastName() {
			return lastName;
		}
		
		public void setLastName(StringProperty lastName) {
			this.lastName = lastName;
		}

		public StringProperty getStreet() {
			return street;
		}
		
		public void setStreet(StringProperty street) {
			this.street = street;
		}

		public StringProperty getNo() {
			return no;
		}
		
		public void setNo(StringProperty no) {
			this.no = no;
		}

		public StringProperty getPostCode() {
			return postCode;
		}
		
		public void setPostCode(StringProperty postCode) {
			this.postCode = postCode;
		}

		public StringProperty getCity() {
			return city;
		}
		
		public void setCity(StringProperty city) {
			this.city = city;
		}

		public StringProperty getCountry() {
			return country;
		}
		
		public void setCountry(StringProperty country) {
			this.country = country;
		}

		public StringProperty getPhone() {
			return phone;
		}
		
		public void setPhone(StringProperty phone) {
			this.phone = phone;
		}

		public StringProperty getMobilePhone() {
			return mobilePhone;
		}
		
		public void setMobilePhone(StringProperty mobilePhone) {
			this.mobilePhone = mobilePhone;
		}

		public StringProperty getFax() {
			return fax;
		}
		
		public void setFax(StringProperty fax) {
			this.fax = fax;
		}

		public StringProperty getEmail() {
			return email;
		}
		
		public void setEmail(StringProperty email) {
			this.email = email;
		}

		public StringProperty getBirthDay() {
			return birthDay;
		}
		
		public void setBirthDay(StringProperty birthDay) {
			this.birthDay = birthDay;
		}

		public StringProperty getBankNo() {
			return bankNo;
		}
		
		public void setBankNo(StringProperty bankNo) {
			this.bankNo = bankNo;
		}

		public StringProperty getBlz() {
			return blz;
		}
		
		public void setBlz(StringProperty blz) {
			this.blz = blz;
		}

		public StringProperty getIban() {
			return iban;
		}

		public void setIban(StringProperty iban) {
			this.iban = iban;
		}

		public StringProperty getBicNo() {
			return bicNo;
		}
		
		public void setBicNo(StringProperty bicNo) {
			this.bicNo = bicNo;
		}
		
		


		public StringProperty getAddress() {
        	StringProperty address =  new SimpleStringProperty(getStreet().get()+" "+getNo().get() + " " + getPostCode().get() + " " +  getCity().get() + " "+ getCountry().get());
		
        	return address;
		}

	}
