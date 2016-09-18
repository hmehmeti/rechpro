package com.rechpro.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.rechpro.persistence.Address;
import com.rechpro.persistence.Customer;
import com.rechpro.ui.EditingCell;
import com.rechpro.ui.IFormRechnung;
import com.rechpro.worker.TestCustomers;
import com.rechpro.worker.UserParameters;
import com.rechpro.worker.VBoxGenerator;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
//	/**
//	 * TODO HME ist noch zu implementieren
//	 */
//	private VBoxGenerator vBoxGenerator;
//	private Stage customerStage;
//	private static Button customerSaveBtn;
//	private static Button customerCancelBtn;
//	VBox customerWindow;
//	TestCustomers testCustomers = new TestCustomers();
//	
//	public KundenArea() {
//		vBoxGenerator = new VBoxGenerator();
//	}
//
//	TableView<Customer> table = new TableView<Customer>();
//	final ObservableList<Customer> data = FXCollections.observableArrayList(
//			testCustomers.getUser1(),
//			testCustomers.getUser2();
//
//	final HBox hb = new HBox();
//
//	protected VBox getTableViewKunde() {
//		final Label label = new Label(IFormRechnung.LBL_TABLE_ADDRESS_CUSTOMERS);
//		label.setFont(new Font("Arial", 20));
//
//		table.setEditable(true);
//		Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
//			public TableCell call(TableColumn p) {
//				return new EditingCell();
//			}
//		};
//
//		TableColumn firstNameCol = new TableColumn(IFormRechnung.LBL_FIRST_NAME);
//		firstNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
//		firstNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
//		firstNameCol.setCellFactory(cellFactory);
//		firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
//			@Override
//			public void handle(CellEditEvent<Customer, String> t) {
//				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
//			}
//		});
//
//		TableColumn lastNameCol = new TableColumn(IFormRechnung.LBL_LAST_NAME);
//		lastNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
//		lastNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
//		lastNameCol.setCellFactory(cellFactory);
//		lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
//			@Override
//			public void handle(CellEditEvent<Customer, String> t) {
//				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
//			}
//		});
//
//		TableColumn emailCol = new TableColumn(IFormRechnung.LBL_EMAIL);
//		emailCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
//		emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
//		emailCol.setCellFactory(cellFactory);
//		emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
//			@Override
//			public void handle(CellEditEvent<Customer, String> t) {
//				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
//			}
//		});
//
//		TableColumn addresseCol = new TableColumn(IFormRechnung.LBL_ADDRESSE);
//		addresseCol.prefWidthProperty().bind(table.widthProperty().multiply(0.35));
//	    addresseCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("addresse"));
//	    addresseCol.setCellFactory(cellFactory);
//		addresseCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
//			@Override
//			public void handle(CellEditEvent<Customer, String> t) {
//				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(spliteAddressAndGetAddressObj(t.getNewValue()));
//			}
//			private Address spliteAddressAndGetAddressObj(String addressString) {
//				String[] stringArray = addressString.split("\\s+");
//				String street = stringArray[0];
//				String no = stringArray[1];
//				String postCode = stringArray[2];
//				String city = stringArray[3];
//				String country = stringArray[4];
//				return new Address(street, no ,postCode, city, country);
//			}
//		});
//
//		table.setItems(data);
//		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, addresseCol);
//
//		final TextField addEmail = new TextField();
//		addEmail.setPromptText(IFormRechnung.LBL_EMAIL);
//		addEmail.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
//
//		VBox createNewCustomerBtnAndText = new VBox(2);
//		Button createNewCustomerBtn = new Button();
//		Text createNewCustomerText = new Text("Erstelle neue Kunde");
//		createNewCustomerBtnAndText.getChildren().addAll(createNewCustomerBtn, createNewCustomerText);
//		createNewCustomerBtn.setGraphic(createImageView("../img/create_new_customer.png", 40, 40));
//		createNewCustomerBtn.setStyle("-fx-font: 5 arial; -fx-base: #b6e7c9;");
//		//Hier wird Stage von Kunde initializiert
//		customerStage = new Stage();
//		customerWindow = vBoxGenerator.createVBox();
//		customerStage.setScene(new Scene(customerWindow));
//		createNewCustomerBtn.setOnAction(event->customerStage.show());
//		customerSaveBtn = vBoxGenerator.getCustomerSaveButton();
//		customerCancelBtn = vBoxGenerator.getCustomerCancelButton();
//		
//		customerSaveBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//		       public void handle(ActionEvent e) {
//		             if(vBoxGenerator.areMandatoryInputsDone()){
//		            	 customPersistor(customerWindow);
//		             }
//		        }
//		    });
//
//		hb.getChildren().addAll(createNewCustomerBtnAndText);
//		hb.setSpacing(4);
//
//		final VBox vbox = new VBox();
//		vbox.setSpacing(5);
//		vbox.setPadding(new Insets(10, 0, 0, 10));
//		vbox.getChildren().addAll(label, table, hb);
//		return vbox;
//	}
//	
//	/**
//	 * create a custom and save in database
//	 * @param customerWindow2
//	 */
//	private void customPersistor(VBox customWindow) {
//		HashMap<Enum, String> customParameterList = new HashMap<Enum, String>();
//		customParameterList.put(UserParameters.SEX, vBoxGenerator.getSexField());
//		customParameterList.put(UserParameters.FIRSTNAME ,vBoxGenerator.getFirstNameField().getText());
//		customParameterList.put(UserParameters.LASTNAME ,vBoxGenerator.getLastNameField().getText());
//		customParameterList.put(UserParameters.STREET ,vBoxGenerator.getStreetField().getText());
//		customParameterList.put(UserParameters.POSTCODE ,vBoxGenerator.getPostCode().getText());
//		customParameterList.put(UserParameters.CITY ,vBoxGenerator.getCity().getText());
//		customParameterList.put(UserParameters.COUNTRY ,vBoxGenerator.getCountry().getText());
//		customParameterList.put(UserParameters.PHONE ,vBoxGenerator.getPhoneField().getText());
//		customParameterList.put(UserParameters.MOBILE_PHONE ,vBoxGenerator.getMobilePhoneField().getText());
//		customParameterList.put(UserParameters.FAX ,vBoxGenerator.getFaxField().getText());
//		customParameterList.put(UserParameters.EMAIL ,vBoxGenerator.getEmailField().getText());
//		customParameterList.put(UserParameters.BIRTHDAY ,vBoxGenerator.getBirthdayField().getText());
//		customParameterList.put(UserParameters.BANK_NO ,vBoxGenerator.getBankNoField().getText());
//		customParameterList.put(UserParameters.BLZ ,vBoxGenerator.getBlzField().getText());
//		customParameterList.put(UserParameters.IBAN ,vBoxGenerator.getIbanField().getText());
//		customParameterList.put(UserParameters.BIC_NO ,vBoxGenerator.getBicNoField().getText());
//		
//		Customer custom = new Customer(customParameterList);
//	}
//	
//	private ImageView createImageView(String imgPath, int width, int hight) {
//		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
//		ImgView.setFitHeight(hight);
//		ImgView.setFitWidth(width);
//		return ImgView;
//	}
}
