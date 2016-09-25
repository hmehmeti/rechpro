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

	private VBoxGenerator vBoxGenerator;
	private Stage customerStage;
	private static Button customerSaveBtn;
	private static Button customerCancelBtn;
	VBox customerWindow;
	TestCustomers testCustomers = new TestCustomers();
	final HBox hb = new HBox();
	TableView<Customer> table = new TableView<Customer>();
	
	public KundenArea() {
		vBoxGenerator = new VBoxGenerator();
	}


	protected VBox getTableViewKunde() {
		final Label label = new Label(IFormRechnung.LBL_TABLE_ADDRESS_CUSTOMERS);
		label.setFont(new Font("Arial", 20));

		final TextField addEmail = new TextField();
		addEmail.setPromptText(IFormRechnung.LBL_EMAIL);
		addEmail.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

		VBox createNewCustomerBtnAndText = new VBox(2);
		Button createNewCustomerBtn = new Button();
		Text createNewCustomerText = new Text("Erstelle neue Kunde");
		createNewCustomerBtnAndText.getChildren().addAll(createNewCustomerBtn, createNewCustomerText);
		createNewCustomerBtn.setGraphic(createImageView("../img/create_new_customer.png", 40, 40));
		createNewCustomerBtn.setStyle("-fx-font: 5 arial; -fx-base: #b6e7c9;");
		//Hier wird Stage von Kunde initializiert
		customerStage = new Stage();
		customerWindow = vBoxGenerator.createVBox();
		customerStage.setScene(new Scene(customerWindow));
		createNewCustomerBtn.setOnAction(event->customerStage.show());
		customerSaveBtn = vBoxGenerator.getCustomerSaveButton();
		customerCancelBtn = vBoxGenerator.getCustomerCancelButton();
		
		customerSaveBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
		       public void handle(ActionEvent e) {
		             if(vBoxGenerator.areMandatoryInputsDone()){
		            	 customPersistor(customerWindow);
		             }
		        }
		    });

		hb.getChildren().addAll(createNewCustomerBtnAndText);
		hb.setSpacing(4);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);
		return vbox;
	}
	
	/**
	 * create a custom and save in database
	 * @param customerWindow2
	 */
	private void customPersistor(VBox customWindow) {
		HashMap<Enum, String> customParameterList = new HashMap<Enum, String>();
		customParameterList.put(UserParameters.SEX, vBoxGenerator.getSexField());
		customParameterList.put(UserParameters.FIRSTNAME ,vBoxGenerator.getFirstNameField().getText());
		customParameterList.put(UserParameters.LASTNAME ,vBoxGenerator.getLastNameField().getText());
		customParameterList.put(UserParameters.STREET ,vBoxGenerator.getStreetField().getText());
		customParameterList.put(UserParameters.POSTCODE ,vBoxGenerator.getPostCode().getText());
		customParameterList.put(UserParameters.CITY ,vBoxGenerator.getCity().getText());
		customParameterList.put(UserParameters.COUNTRY ,vBoxGenerator.getCountry().getText());
		customParameterList.put(UserParameters.PHONE ,vBoxGenerator.getPhoneField().getText());
		customParameterList.put(UserParameters.MOBILE_PHONE ,vBoxGenerator.getMobilePhoneField().getText());
		customParameterList.put(UserParameters.FAX ,vBoxGenerator.getFaxField().getText());
		customParameterList.put(UserParameters.EMAIL ,vBoxGenerator.getEmailField().getText());
		customParameterList.put(UserParameters.BIRTHDAY ,vBoxGenerator.getBirthdayField().getText());
		customParameterList.put(UserParameters.BANK_NO ,vBoxGenerator.getBankNoField().getText());
		customParameterList.put(UserParameters.BLZ ,vBoxGenerator.getBlzField().getText());
		customParameterList.put(UserParameters.IBAN ,vBoxGenerator.getIbanField().getText());
		customParameterList.put(UserParameters.BIC_NO ,vBoxGenerator.getBicNoField().getText());
		
		Customer custom = new Customer(customParameterList);
	}
	
	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}
}
