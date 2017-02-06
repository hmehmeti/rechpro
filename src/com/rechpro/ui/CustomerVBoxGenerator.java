package com.rechpro.ui;

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
	public static final String KEINE_OBLIGATORISCHE_FELDER = "Obligatorische Felder sind nicht eingegeben!";

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
		sexField = new ChoiceBox<String>(FXCollections.observableArrayList("", "Frau", "Herr"));
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
//		streetAndHomeNrField.setStyle(VALID_TEXTFIELD_CSS);
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
		window = new VBox(VBOX_SPACING);
		HBox mainWindow = new HBox();
		window.setMargin(mainWindow, new Insets(10, 10, 10, 10));
		
		Label customerData = new Label(CREATE_CUSTOMER_LABEL);
		customerData.setFont(Font.font("Roboto", 20));
		window.setMargin(customerData, new Insets(10, 10, 10, 10));

		VBox firstColumn = new VBox(COLUMN_SPACING);
		Text sex = new Text("Anrede*");
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
		firstColumn.getChildren().addAll(sex, firstName, lastName, street, postCodePlaceCountry, phone, mobilePhone,
				fax, email, birthday, bankNo, blz, iban, bicNo);
		setSize(firstColumn, COLUMN_TEXT_SIZE);
		
		VBox secondColumn = new VBox(COLUMN_SPACING);
		for (int i = 0; i < firstColumn.getChildren().size(); i++) {
			secondColumn.getChildren().add(new Text(" : "));
		}
		setSize(secondColumn, COLUMN_TEXT_SIZE);
		
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
		window.setMargin(infoMsgBox, new Insets(10, 10, 10, 10));
		
		HBox buttons = new HBox(SPACE_BETWEEN_BUTTONS);
		cancelBtn = new Button("Abbrechen");
		saveBtn = new Button("Speichern");
		buttons.getChildren().addAll(cancelBtn, saveBtn);
		window.setMargin(buttons, new Insets(10, 10, 10, 10));
		
		window.setMargin(buttons, new Insets(10, 10, 10, 10));
		window.getChildren().addAll(customerData, mainWindow, infoMsgBox, buttons);
		return window;
	}
	
	protected void resetInputValues(VBox customerCreateWindow) {
		Object hBoxObject = customerCreateWindow.getChildren().get(1);
		if (hBoxObject instanceof HBox) {
			HBox mainWindow = (HBox) hBoxObject;
			Object vBoxObject = mainWindow.getChildren().get(2);
			if (vBoxObject instanceof VBox) {
				VBox thirdColumn = (VBox) vBoxObject;
				for (int i = 0; i < thirdColumn.getChildren().size(); i++) {
					Object columnObject = thirdColumn.getChildren().get(i);
					if (columnObject instanceof TextField) {
						((TextField) columnObject).setText("");
					} else if (columnObject instanceof ChoiceBox) {
						((ChoiceBox) columnObject).setValue(new String(""));;
					} else if (columnObject instanceof HBox) {
						// here should be the address fields
						HBox otherHBoxObject = (HBox) columnObject;
						for (Object otherObject : otherHBoxObject.getChildren()) {
							if (otherObject instanceof TextField) {
								((TextField) otherObject).setText("");
							}
						}
					}
				}
			}
		}
		setInfoMsg(customerCreateWindow, "");
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
	
	public boolean areMandatoryInputsDone() {
		boolean result = true;
		if(sexField.getSelectionModel().isEmpty()){
			firstNameField.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if(StringUtils.isEmpty(firstNameField.getText())){
			firstNameField.setStyle(INVALID_TEXTFIELD_CSS);
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
