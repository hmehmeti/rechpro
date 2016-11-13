package com.rechpro.worker;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VBoxGenerator {
	
	private static Button customerSaveBtn;
	private static Button customerCancelBtn;
	private static VBox customWindow;
	private String invalidTextFildCSS = "-fx-border-color: red;";
	private String validTextFieldCSS = "-fx-border-color: #DCDCDC;";
	
	ChoiceBox<String> sexField;
	TextField firstNameField;
	TextField lastNameField;
	
	HBox streetAndHomeNrField;
	TextField streetField;
	TextField homeNrField;

	HBox postCodeCityCountryField;
	TextField postCode;
	TextField city;
	TextField country;

	TextField phoneField;
	TextField mobilePhoneField;
	TextField faxField;
	TextField emailField;
	TextField birthdayField;
	TextField bankNoField;
	TextField blzField;
	TextField ibanField;
	TextField bicNoField;
	
	public VBoxGenerator(){
		sexField = new ChoiceBox<String>(FXCollections.observableArrayList("Mann", "Frau", "Firma"));
		firstNameField = new TextField();
		lastNameField = new TextField();
		
		streetAndHomeNrField = new HBox(2);
		streetField = new TextField();
		homeNrField = new TextField();

		postCodeCityCountryField = new HBox(2);
		postCode = new TextField();
		city = new TextField();
		country = new TextField();

		phoneField = new TextField();
		mobilePhoneField = new TextField();
		faxField = new TextField();
		emailField = new TextField();
		birthdayField = new TextField();
		bankNoField = new TextField();
		blzField = new TextField();
		ibanField = new TextField();
		bicNoField = new TextField();
	}

	public VBox createCustomerCreationTable() {

		customWindow = new VBox(10);

		HBox mainWindow = new HBox();
		customWindow.setMargin(mainWindow, new Insets(10, 10, 10, 10));
		Label customerData = new Label("Kunde Erstellen");
		customWindow.setMargin(customerData, new Insets(10, 10, 10, 10));
		customerData.setFont(Font.font("Roboto", 20));

		VBox firtsColumn = new VBox(8);

		Text sex = new Text("Andrede*");
		Text firstName = new Text("Vorname*");
		Text lastName = new Text("Nachname");
		Text street = new Text("Straﬂe/Nr.*");
		Text postCodePlaceCountry = new Text("PLZ/Ort/Land*");
		Text phone = new Text("Telefon Nr.");
		Text mobilePhone = new Text("Handy Nr.");
		Text fax = new Text("Fax");
		Text email = new Text("EMail");
		Text birthday = new Text("Geburtsort");
		Text bankNo = new Text("Konto Nr.*");
		Text blz = new Text("BLZ*");
		Text iban = new Text("IBAN");
		Text bicNo = new Text("BIC");
		firtsColumn.getChildren().addAll(sex, firstName, lastName, street, postCodePlaceCountry, phone, mobilePhone,
				fax, email, birthday, bankNo, blz, iban, bicNo);
		setSize(firtsColumn, 15);
		
		VBox secondColumn = new VBox(8);
		for (int i = 0; i < firtsColumn.getChildren().size(); i++) {
			secondColumn.getChildren().add(new Text(" : "));
		}
		setSize(secondColumn, 15);
		VBox thirdColumn = new VBox(3);
		
		streetAndHomeNrField.getChildren().addAll(streetField, homeNrField);
		postCodeCityCountryField.getChildren().addAll(postCode, city, country);

		thirdColumn.getChildren().addAll(sexField, firstNameField, lastNameField, streetAndHomeNrField, postCodeCityCountryField,
				phoneField, mobilePhoneField, faxField, emailField, birthdayField, bankNoField, blzField, ibanField, bicNoField);

		mainWindow.getChildren().addAll(firtsColumn, secondColumn, thirdColumn);
		HBox buttons = new HBox(20);
		customerCancelBtn = new Button("Abbrechen");
		customerSaveBtn = new Button("Speichern");
		buttons.getChildren().addAll(customerCancelBtn, customerSaveBtn);
		customWindow.setMargin(buttons, new Insets(10, 10, 10, 10));
		customWindow.getChildren().addAll(customerData, mainWindow, buttons);
		return customWindow;
	}
	
	public Button getCustomerSaveButton(){
		return customerSaveBtn;
	}
	public Button getCustomerCancelButton(){
		return customerCancelBtn;
	}

	private static void setSize(VBox firstColumn, int textSize) {

		for (int i = 0; i < firstColumn.getChildren().size(); i++) {
			Text text = (Text) firstColumn.getChildren().get(i);
			text.setFont(Font.font("Roboto", textSize));
		}
	}
	
	public boolean areMandatoryInputsDone() {

		boolean returnValue = true;
		
		if(sexField.getSelectionModel().isEmpty() || sexField.getSelectionModel().equals(null)){
			firstNameField.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			//TODO maybe it should set on default
			sexField.setStyle(validTextFieldCSS);
		}
		if(firstNameField.getText().isEmpty() || firstNameField.getText().equals(null)){
			firstNameField.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			//TODO maybe it should set on default
			firstNameField.setStyle(validTextFieldCSS);
		}
		if(streetField.getText().isEmpty() || streetField.getText().equals(null)){
			streetField.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			streetField.setStyle(validTextFieldCSS);
		}
		if(postCode.getText().isEmpty() || postCode.getText().equals(null)){
			postCode.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			postCode.setStyle(validTextFieldCSS);
		}
		if(city.getText().isEmpty() || city.getText().equals(null)){
			city.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			city.setStyle(validTextFieldCSS);
		}
		if(country.getText().isEmpty() || country.getText().equals(null)){
			country.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			country.setStyle(validTextFieldCSS);
		}
		if(bankNoField.getText().isEmpty() || bankNoField.getText().equals(null)){
			bankNoField.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			bankNoField.setStyle(validTextFieldCSS);
		}
		if(blzField.getText().isEmpty() || blzField.getText().equals(null)){
			blzField.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			blzField.setStyle(validTextFieldCSS);
		}
		return returnValue;
	}
	
	public static VBox getCustomWindow() {
		return customWindow;
	}

	public String getSexField() {
		return sexField.getSelectionModel().getSelectedItem();
	}

	public TextField getFirstNameField() {
		return firstNameField;
	}

	public TextField getLastNameField() {
		return lastNameField;
	}

	public TextField getStreetField() {
		return (TextField) streetAndHomeNrField.getChildren().get(0);
	}
	
	public TextField getHomeNrField() {
		return (TextField) streetAndHomeNrField.getChildren().get(1);
	}

	public HBox getPostCodeCityCountryField() {
		return postCodeCityCountryField;
	}

	public TextField getPostCode() {
		return postCode;
	}

	public TextField getCity() {
		return city;
	}

	public TextField getCountry() {
		return country;
	}

	public TextField getPhoneField() {
		return phoneField;
	}

	public TextField getMobilePhoneField() {
		return mobilePhoneField;
	}

	public TextField getFaxField() {
		return faxField;
	}

	public TextField getEmailField() {
		return emailField;
	}

	public TextField getBirthdayField() {
		return birthdayField;
	}

	public TextField getBankNoField() {
		return bankNoField;
	}

	public TextField getBlzField() {
		return blzField;
	}

	public TextField getIbanField() {
		return ibanField;
	}

	public TextField getBicNoField() {
		return bicNoField;
	}
	 
}
