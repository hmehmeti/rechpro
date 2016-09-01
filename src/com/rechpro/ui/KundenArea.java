package com.rechpro.ui;

import com.rechpro.persistence.Address;
import com.rechpro.persistence.Customer;
import com.rechpro.ui.EditingCell;
import com.rechpro.ui.IFormRechnung;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author hmehmeti
 *
 */
public class KundenArea {
	/**
	 * TODO HME ist noch zu implementieren
	 */
	public KundenArea() {
	}

	TableView<Customer> table = new TableView<Customer>();
	private Address gundiAddress = new Address("Gundistr.", "12", "44488", "Karlsruhe", "Deutschland");
	private Address maxAddress = new Address("Gundistr.", "65", "44488", "Stuttgart", "Deutschland");
	private Address tayipAddress = new Address("Gundistr.", "10", "44488", "Dortmund", "Deutschland");
	private Address merkelAddress = new Address("Gundistr.", "3", "44488", "Karlsruhe", "Deutschland");
	final ObservableList<Customer> data = FXCollections.observableArrayList(
			new Customer("Gundi", "Gundieren", "gundi.gundieren@gundi.com", gundiAddress),
			new Customer("Max", "Mustermann", "max.mustermann@example.com", maxAddress),
			new Customer("Tayip", "Merkel", "tayip.merkel@kilimili.com", tayipAddress),
			new Customer("Putin", "Obama", "putin.obama@freunde.com", merkelAddress));

	final HBox hb = new HBox();

	protected VBox getTableViewKunde() {

		final Label label = new Label(IFormRechnung.LBL_TABLE_ADDRESS_CUSTOMERS);
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);
		Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};

		TableColumn firstNameCol = new TableColumn(IFormRechnung.LBL_FIRST_NAME);
		firstNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		firstNameCol.setCellFactory(cellFactory);
		firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
			}
		});

		TableColumn lastNameCol = new TableColumn(IFormRechnung.LBL_LAST_NAME);
		lastNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		lastNameCol.setCellFactory(cellFactory);
		lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
			}
		});

		TableColumn emailCol = new TableColumn(IFormRechnung.LBL_EMAIL);
		emailCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		emailCol.setCellFactory(cellFactory);
		emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			}
		});

		TableColumn addresseCol = new TableColumn(IFormRechnung.LBL_ADDRESSE);
		addresseCol.prefWidthProperty().bind(table.widthProperty().multiply(0.35));
	    addresseCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("addresse"));
	    addresseCol.setCellFactory(cellFactory);
		addresseCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(spliteAddressAndGetAddressObj(t.getNewValue()));
			}
			private Address spliteAddressAndGetAddressObj(String addressString) {
				String[] stringArray = addressString.split("\\s+");
				String street = stringArray[0];
				String no = stringArray[1];
				String postCode = stringArray[2];
				String city = stringArray[3];
				String country = stringArray[4];
				return new Address(street, no ,postCode, city, country);
			}
		});

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, addresseCol);

		final TextField addFirstName = new TextField();
		addFirstName.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
		addFirstName.setPromptText(IFormRechnung.LBL_FIRST_NAME);

		final TextField addLastName = new TextField();
		addLastName.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
		addLastName.setPromptText(IFormRechnung.LBL_LAST_NAME);

		final TextField addEmail = new TextField();
		addEmail.setPromptText(IFormRechnung.LBL_EMAIL);
		addEmail.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

		final Button addAddress = new Button("Addresse eingeben!");
		addAddress.prefWidthProperty().bind(table.widthProperty().multiply(0.35));
		addAddress.setOnAction(event->showStage());
		
		//addAddress.setPromptText(IFormRechnung.LBL_ADDRESSE);

		final Button addButton = new Button(IFormRechnung.BTN_KUNDEN_ADD);

		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.add(new Customer(addFirstName.getText(), addLastName.getText(), addEmail.getText(), spliteAddressAndGetAddressObj(addAddress.getText())));
				addFirstName.clear();
				addLastName.clear();
				addEmail.clear();
				//addAddress.clear();
			}

			private Address spliteAddressAndGetAddressObj(String addressString) {
				String street = "";
				String no = "";
				String postCode = "";
				String country="";
				String city = "";
				if (addressString != null || addressString != "") {
					String[] stringArray = addressString.split("\\s+");

					if(stringArray.length >= 1)
						street = stringArray[0];
					if(stringArray.length >= 2)
						no = stringArray[1];
					if(stringArray.length >= 3)
						postCode = stringArray[2];
					if (stringArray.length >= 4)
						city = stringArray[3];
					if (stringArray.length >= 5)
						country = stringArray[4];
				}
				return new Address(street, no, postCode, city, country);

			}
		});

		hb.getChildren().addAll(addFirstName, addLastName, addEmail, addAddress, addButton);
		hb.setSpacing(4);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);
		return vbox;
	}
	
	public static void showStage(){
		
		Stage newStage = new Stage();
		VBox customWindow = new VBox(10);
		
		HBox mainWindow = new HBox();
		customWindow.setMargin(mainWindow, new Insets(10, 10, 10, 10));
		Label customerData = new Label("Kunde Erstellen");
		customWindow.setMargin(customerData, new Insets(10, 10, 10, 10));
		customerData.setFont(Font.font ("Roboto", 20));
		
		
		VBox firtsColumn = new VBox(8);
		
		Text sex = new Text("Andrede");
		Text firstName = new Text("Vorname");
		Text lastName = new Text("Nachname");
		Text street = new Text("Straﬂe");
		Text postCodePlaceCountry = new Text("PLZ/Ort/Land");
		Text phone = new Text("Telefon Nr.");
		Text mobilePhone = new Text("Handy Nr.");
		Text fax = new Text("Fax");
		Text email = new Text("EMail");
		Text birthday = new Text("Geburtsort");
		Text bankNo = new Text("Konto Nr.");
		Text blz = new Text("BLZ");
		Text iban = new Text("IBAN");
		Text bicNo = new Text("BIC");
		firtsColumn.getChildren().addAll(sex, firstName, lastName, street, postCodePlaceCountry, phone, mobilePhone, fax, email, birthday,bankNo, blz, iban, bicNo);
		setSize(firtsColumn, 15);
		VBox secondColumn = new VBox(8);
		for (int i = 0; i < firtsColumn.getChildren().size(); i++){
			secondColumn.getChildren().add(new Text(" : "));
		}
		setSize(secondColumn, 15);
		VBox thirdColumn = new VBox(3);
		ChoiceBox sexField = new ChoiceBox(FXCollections.observableArrayList(
			    "Mann", "Frau", "Firma")
			);
		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		TextField streetField = new TextField();
		HBox postCodeCityCountryField = new HBox(2);
		TextField postCode = new TextField();
		TextField city = new TextField();
		TextField country = new TextField();
		postCodeCityCountryField.getChildren().addAll(postCode, city,country);
		TextField phoneField = new TextField();
		TextField mobilePhoneField = new TextField();
		TextField faxField = new TextField();
		TextField emailField = new TextField();
		TextField birthdayField = new TextField();
		TextField bankNoField = new TextField();
		TextField blzField = new TextField();
		TextField ibanField = new TextField();
		TextField bicNoField = new TextField();
		
		thirdColumn.getChildren().addAll(sexField, firstNameField, lastNameField, streetField, postCodeCityCountryField, phoneField, mobilePhoneField, faxField, emailField, birthdayField, bankNoField, blzField, ibanField, bicNoField);
		
		mainWindow.getChildren().addAll(firtsColumn, secondColumn, thirdColumn);
		HBox buttons = new HBox(20);
		Button cancel = new Button("Abbrechen");
		Button save = new Button("Speichern");
		buttons.getChildren().addAll(cancel, save);
		customWindow.setMargin(buttons, new Insets(10, 10, 10, 10));
		
		customWindow.getChildren().addAll(customerData, mainWindow, buttons);
		
		Scene stageScene = new Scene(customWindow);
		newStage.setScene(stageScene);
		newStage.show();
		}

	private static void setSize(VBox firstColumn, int textSize) {
		
		for (int i= 0; i< firstColumn.getChildren().size(); i++){
			Text text = (Text)firstColumn.getChildren().get(i);
			text.setFont(Font.font ("Roboto", textSize));
		}
			
	}
}
