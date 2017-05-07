package com.rechpro.ui;

import java.io.IOException;
import java.util.HashMap;

import com.rechpro.viewmodel.CustomerViewModel;
import com.rechpro.worker.CustomerController;
import com.rechpro.worker.UserParameters;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.PathClass;

/**
 * @author hmehmeti
 *
 */
public class CustomerArea {

	private static final int BUTTON_WIDTH_AND_HEIGHT = 20;
	private static final int CUSTOMER_AREA_VBOX_SPACNG = 5;
	private static final String NEW_CUSTOMER_BUTTON_TEXT = "Erstelle neue Kunde";
	private static final int VBOX_SPACING = 2;
	private CustomerVBoxGenerator customerGenerator;
	private CustomerController customController;
	private Stage customerStage;
	private StackPane stackPane;
	
	public CustomerArea() {
		customController = new CustomerController();
		customerGenerator = new CustomerVBoxGenerator();
		customerStage = new Stage();
		stackPane = new StackPane();
		loadCustomerSelectionArea();
		
		//stackPane.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
	}
	
	protected VBox getTableViewKunde() {
		VBox customerArea = new VBox();
		// Button and Text to create new customer
		VBox createNewCustomerBtnAndText = new VBox(VBOX_SPACING);
		Button createNewCustomerBtn = getNewCustomerCreatButtonWithText();
		createNewCustomerBtnAndText.getChildren().addAll(createNewCustomerBtn, new Text(NEW_CUSTOMER_BUTTON_TEXT));
		
		customerArea.setSpacing(CUSTOMER_AREA_VBOX_SPACNG);
		customerArea.setPadding(new Insets(10, 0, 0, 10));
		customerArea.getChildren().addAll(stackPane, createNewCustomerBtnAndText);
		return customerArea;
	}
	
	private Button getNewCustomerCreatButtonWithText() {
		Button createNewCustomerBtn = new Button();
		createNewCustomerBtn.setGraphic(customerGenerator.createImageView(this.getClass(), PathClass.ADD_BTN,
				BUTTON_WIDTH_AND_HEIGHT, BUTTON_WIDTH_AND_HEIGHT));
		createNewCustomerBtn.setStyle(CustomerVBoxGenerator.BUTTON_STYLE);

		// new window if createNewCustomerButton is clicked
		VBox customerCreateWindow = customerGenerator.createCustomerCreationTable();
		customerStage.setScene(new Scene(customerCreateWindow));
		createNewCustomerBtn.setOnAction(event -> {
			customerGenerator.resetInputValues(customerCreateWindow);
			customerGenerator.setTextStyleToValid();
			customerStage.show();
		});
		Button customerSaveBtn = customerGenerator.getSaveButton();
		Button customerCancelBtn = customerGenerator.getCancelButton();
		customerSaveBtn.setOnAction(event -> {
			checkMandatoryFieldsAndSaveCustomer(customerCreateWindow);
		});
		customerCancelBtn.setOnAction(event -> customerStage.close());

		return createNewCustomerBtn;
	}

	private void checkMandatoryFieldsAndSaveCustomer(VBox customerCreateWindow) {
		if(customerGenerator.areMandatoryInputsDone()){
       	 transformAndPersist();
       	 loadCustomerSelectionArea();
       	 customerStage.close();
        } else {
        	customerGenerator.setInfoMsg(customerCreateWindow, VBoxGenerator.MISSED_REQUIRED_FIELD);
        }
	}

	private void transformAndPersist() {
		HashMap<Enum, String> customParameterList = new HashMap<Enum, String>();
		customParameterList.put(UserParameters.SEX, customerGenerator.getSexField());
		customParameterList.put(UserParameters.FIRSTNAME ,customerGenerator.getFirstNameField().getText());
		customParameterList.put(UserParameters.LASTNAME ,customerGenerator.getLastNameField().getText());
		customParameterList.put(UserParameters.STREET ,customerGenerator.getStreetField().getText());
		customParameterList.put(UserParameters.HOME_NO ,customerGenerator.getHomeNrField().getText());
		customParameterList.put(UserParameters.POSTCODE ,customerGenerator.getPostCode().getText());
		customParameterList.put(UserParameters.CITY ,customerGenerator.getCity().getText());
		customParameterList.put(UserParameters.COUNTRY ,customerGenerator.getCountry().getText());
		customParameterList.put(UserParameters.PHONE ,customerGenerator.getPhoneField().getText());
		customParameterList.put(UserParameters.MOBILE_PHONE ,customerGenerator.getMobilePhoneField().getText());
		customParameterList.put(UserParameters.FAX ,customerGenerator.getFaxField().getText());
		customParameterList.put(UserParameters.EMAIL ,customerGenerator.getEmailField().getText());
		customParameterList.put(UserParameters.BIRTHDAY ,customerGenerator.getBirthdayField().getText());
		customParameterList.put(UserParameters.BANK_NO ,customerGenerator.getBankNoField().getText());
		customParameterList.put(UserParameters.BLZ ,customerGenerator.getBlzField().getText());
		customParameterList.put(UserParameters.IBAN ,customerGenerator.getIbanField().getText());
		customParameterList.put(UserParameters.BIC_NO ,customerGenerator.getBicNoField().getText());
		
		customController.transformAndPersist(customParameterList);
	}
	
	/**
	 * Hier wird die Tabelle, in der die Kunden gelistet und gesucht werden kann, erzugt und in CustomerArea hinzugefügt
	 */
	private void loadCustomerSelectionArea() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonTable.fxml"));
		try {
			AnchorPane customerSelectionArea = (AnchorPane) loader.load();
			stackPane.getChildren().add(customerSelectionArea);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
