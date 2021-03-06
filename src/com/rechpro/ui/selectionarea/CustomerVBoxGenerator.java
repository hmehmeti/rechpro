package com.rechpro.ui.selectionarea;

import org.apache.commons.lang3.StringUtils;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CustomerVBoxGenerator extends VBoxGenerator {
	
	private static final String CREATE_CUSTOMER_LABEL = "Kunde erstellen";
	private static final String[] ANREDE_VALUES = {"Firma", "Frau", "Herr"};
	
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
	
	public CustomerVBoxGenerator(){
		initialiseCustomerFields();
		setTextStyleToValid();
	}
	
	public void initialiseCustomerFields() {
		sexField = new ChoiceBox<String>(FXCollections.observableArrayList(ANREDE_VALUES));
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
	
	protected void setTextStyleToValid() {
		sexField.setStyle(VALID_TEXTFIELD_CSS);
		firstNameField.setStyle(VALID_TEXTFIELD_CSS);
		lastNameField.setStyle(VALID_TEXTFIELD_CSS);
		streetField.setStyle(VALID_TEXTFIELD_CSS);
		homeNrField.setStyle(VALID_TEXTFIELD_CSS);
		postCodeCityCountryField.setStyle(VALID_TEXTFIELD_CSS);
		postCode.setStyle(VALID_TEXTFIELD_CSS);
		city.setStyle(VALID_TEXTFIELD_CSS);
		country.setStyle(VALID_TEXTFIELD_CSS);
		phoneField.setStyle(VALID_TEXTFIELD_CSS);
		mobilePhoneField.setStyle(VALID_TEXTFIELD_CSS);
		faxField.setStyle(VALID_TEXTFIELD_CSS);
		emailField.setStyle(VALID_TEXTFIELD_CSS);
		birthdayField.setStyle(VALID_TEXTFIELD_CSS);
		bankNoField.setStyle(VALID_TEXTFIELD_CSS);
		blzField.setStyle(VALID_TEXTFIELD_CSS);
		ibanField.setStyle(VALID_TEXTFIELD_CSS);
		bicNoField.setStyle(VALID_TEXTFIELD_CSS);
	}

	public VBox createCustomerCreationTable() {
		VBox window = new VBox(VBOX_SPACING);
		HBox mainWindow = new HBox();
		VBox.setMargin(mainWindow, new Insets(10, 10, 10, 10));
		
		Label customerDataLabel = new Label(CREATE_CUSTOMER_LABEL);
		customerDataLabel.setFont(Font.font("Roboto", 20));
		VBox.setMargin(customerDataLabel, new Insets(10, 10, 10, 10));

		VBox firstColumn = new VBox(COLUMN_SPACING);
		Text sex = new Text("Anrede*");
		Text firstName = new Text("Vorname*");
		Text lastName = new Text("Nachname*");
		Text street = new Text("Stra�e/Nr.*");
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
		firstColumn.getChildren().addAll(sex, firstName, lastName, street, postCodePlaceCountry, phone, mobilePhone,
				fax, email, birthday, bankNo, blz, iban, bicNo);
		setTextFontAndSize(firstColumn, LABEL_FONT, COLUMN_TEXT_SIZE);
		
		VBox secondColumn = new VBox(COLUMN_SPACING);
		for (int i = 0; i < firstColumn.getChildren().size(); i++) {
			secondColumn.getChildren().add(new Text(" : "));
		}
		setTextFontAndSize(secondColumn, LABEL_FONT, COLUMN_TEXT_SIZE);
		
		VBox thirdColumn = new VBox(VALUE_COLUMN_SPACING);		
		streetAndHomeNrField.getChildren().addAll(streetField, homeNrField);
		postCodeCityCountryField.getChildren().addAll(postCode, city, country);
		thirdColumn.getChildren().addAll(sexField, firstNameField, lastNameField, streetAndHomeNrField, postCodeCityCountryField,
				phoneField, mobilePhoneField, faxField, emailField, birthdayField, bankNoField, blzField, ibanField, bicNoField);

		mainWindow.getChildren().addAll(firstColumn, secondColumn, thirdColumn);
		
		HBox infoMsgBox = new HBox();
		Text infoMsg = new Text();
		infoMsg.setFill(Color.RED);
		infoMsgBox.getChildren().add(infoMsg);
		VBox.setMargin(infoMsgBox, new Insets(10, 10, 10, 10));
		
		HBox buttons = new HBox(SPACE_BETWEEN_BUTTONS);
		Button cancelBtn = new Button("Abbrechen");
		setCancelButton(cancelBtn);
		Button saveBtn = new Button("Speichern");
		setSaveButton(saveBtn);
		buttons.getChildren().addAll(saveBtn, cancelBtn);
		VBox.setMargin(buttons, new Insets(10, 10, 10, 10));
		
		window.getChildren().addAll(customerDataLabel, mainWindow, infoMsgBox, buttons);
		return window;
	}
	
	public void setInfoMsg(VBox customerCreateWindow, String msg) {
		Object windowObject = customerCreateWindow.getChildren().get(2);
    	if (windowObject instanceof HBox) {
    		HBox infoMsgBox = (HBox) windowObject;
    		Object infoObject = infoMsgBox.getChildren().get(0);
        	if (infoObject instanceof Text) {
        		Text infoMsg = (Text) infoObject;
        		infoMsg.setText(msg);
        	}
    	}
	}
	
	@Override
	public boolean areMandatoryInputsDone() {
		boolean result = true;
		if(sexField.getSelectionModel().isEmpty()){
			sexField.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(firstNameField.getText())){
			firstNameField.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(lastNameField.getText())){
			lastNameField.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(streetField.getText())){
			streetField.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(homeNrField.getText())){
			homeNrField.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(postCode.getText())){
			postCode.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(city.getText())){
			city.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(country.getText())){
			country.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(bankNoField.getText())){
			bankNoField.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(blzField.getText())){
			blzField.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		return result;
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
