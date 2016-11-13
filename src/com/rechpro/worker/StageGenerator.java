package com.rechpro.worker;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class StageGenerator {
	
	private static Button customerSaveBtn;
	private static Button customerCancelBtn;
	private static VBox customWindow;
	private String invalidTextFildCSS = "-fx-border-color: red;";
	private String validTextFieldCSS = "-fx-border-color: #DCDCDC;";

	public static VBox createCustomerStage() {

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
		Text street = new Text("Straﬂe*");
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
		ChoiceBox<String> sexField = new ChoiceBox<String>(FXCollections.observableArrayList("Mann", "Frau", "Firma"));
		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		TextField streetField = new TextField();

		HBox postCodeCityCountryField = new HBox(2);
		TextField postCode = new TextField();
		TextField city = new TextField();
		TextField country = new TextField();
		postCodeCityCountryField.getChildren().addAll(postCode, city, country);

		TextField phoneField = new TextField();
		TextField mobilePhoneField = new TextField();
		TextField faxField = new TextField();
		TextField emailField = new TextField();
		TextField birthdayField = new TextField();
		TextField bankNoField = new TextField();
		TextField blzField = new TextField();
		TextField ibanField = new TextField();
		TextField bicNoField = new TextField();

		thirdColumn.getChildren().addAll(sexField, firstNameField, lastNameField, streetField, postCodeCityCountryField,
				phoneField, mobilePhoneField, faxField, emailField, birthdayField, bankNoField, blzField, ibanField,
				bicNoField);

		mainWindow.getChildren().addAll(firtsColumn, secondColumn, thirdColumn);
		HBox buttons = new HBox(20);
		customerCancelBtn = new Button("Abbrechen");
		customerSaveBtn = new Button("Speichern");
		buttons.getChildren().addAll(customerCancelBtn, customerSaveBtn);
		customWindow.setMargin(buttons, new Insets(10, 10, 10, 10));

		customWindow.getChildren().addAll(customerData, mainWindow, buttons);

		//Scene stageScene = new Scene(customWindow);
		//newStage.setScene(stageScene);
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

		HBox mainWindow = (HBox) customWindow.getChildren().get(1);
		VBox thirdColumn = (VBox) mainWindow.getChildren().get(2);
		ChoiceBox<String> sexField = (ChoiceBox<String>) thirdColumn.getChildren().get(0);
		TextField firstNameField = (TextField) thirdColumn.getChildren().get(1);
		TextField streetField = (TextField) thirdColumn.getChildren().get(2);
		HBox plzCityLand = (HBox) thirdColumn.getChildren().get(4);
		TextField plz = (TextField) plzCityLand.getChildren().get(0);
		TextField city = (TextField) plzCityLand.getChildren().get(1);
		TextField country = (TextField) plzCityLand.getChildren().get(2);
		boolean returnValue = true;
		TextField bankNo = (TextField) thirdColumn.getChildren().get(9);
		TextField blz = (TextField) thirdColumn.getChildren().get(10);
		
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
		if(plz.getText().isEmpty() || plz.getText().equals(null)){
			plz.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			plz.setStyle(validTextFieldCSS);
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
		if(bankNo.getText().isEmpty() || bankNo.getText().equals(null)){
			bankNo.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			bankNo.setStyle(validTextFieldCSS);
		}
		if(blz.getText().isEmpty() || blz.getText().equals(null)){
			blz.setStyle(invalidTextFildCSS);
			returnValue = false;
		}else{
			blz.setStyle(validTextFieldCSS);
		}
		return returnValue;
	}
	 
}
