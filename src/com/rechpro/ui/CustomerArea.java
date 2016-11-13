package com.rechpro.ui;

import java.io.IOException;
import java.util.HashMap;

import com.rechpro.worker.CustomerController;
import com.rechpro.worker.UserParameters;
import com.rechpro.worker.VBoxGenerator;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.PathClass;

/**
 * @author hmehmeti
 *
 */
public class CustomerArea {

	private VBoxGenerator customerTableGenerator;
	private Stage customerStage;
	private static Button customerSaveBtn;
	private static Button customerCancelBtn;
	final HBox hb = new HBox();
	private StackPane stackPane = new StackPane();
	private CustomerController customController;
	
	public CustomerArea() {
		customController = new CustomerController();
		customerTableGenerator = new VBoxGenerator();
		addCustomerSelectionArea();
		
		//stackPane.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
	}
	
	protected VBox getTableViewKunde() {
		VBox customerArea = new VBox(2);
		// Button and Text to create new customer
		VBox createNewCustomerBtnAndText = new VBox(2);
		Button createNewCustomerBtn = getNewCustomerCreatButtonWithText();
		Text createNewCustomerText = new Text("Erstelle neue Kunde");
		createNewCustomerBtnAndText.getChildren().addAll(createNewCustomerBtn, createNewCustomerText);
		
		// new window if createNewCustomerButton is clicked
		customerStage = new Stage();
		VBox customCreateWindow = customerTableGenerator.createCustomerCreationTable();
		customerStage.setScene(new Scene(customCreateWindow));
		createNewCustomerBtn.setOnAction(event->customerStage.show());
		customerSaveBtn = customerTableGenerator.getCustomerSaveButton();
		customerCancelBtn = customerTableGenerator.getCustomerCancelButton();
		customerSaveBtn.setOnAction(event->checkMandatoryFieldAndSaveCustomer());
		customerCancelBtn.setOnAction(event->customerStage.close());

		customerArea.setSpacing(5);
		customerArea.setPadding(new Insets(10, 0, 0, 10));
		customerArea.getChildren().addAll(stackPane, createNewCustomerBtnAndText);
		return customerArea;
	}

	private void checkMandatoryFieldAndSaveCustomer() {
		if(customerTableGenerator.areMandatoryInputsDone()){
       	 transformAndPersist();
        } else {
        	System.out.println("Obligatorische Felder sind nicht eingegeben");
        }
	}

	private Button getNewCustomerCreatButtonWithText(){
		
		Button createNewCustomerBtn = new Button();
		createNewCustomerBtn.setGraphic(createImageView(PathClass.ADD_NEW_CUSTOMER_BTN, 40, 40));
		createNewCustomerBtn.setStyle("-fx-font: 5 arial; -fx-base: #b6e7c9;");
		return createNewCustomerBtn;
	}

	private void transformAndPersist() {
		HashMap<Enum, String> customParameterList = new HashMap<Enum, String>();
		customParameterList.put(UserParameters.SEX, customerTableGenerator.getSexField());
		customParameterList.put(UserParameters.FIRSTNAME ,customerTableGenerator.getFirstNameField().getText());
		customParameterList.put(UserParameters.LASTNAME ,customerTableGenerator.getLastNameField().getText());
		customParameterList.put(UserParameters.STREET ,customerTableGenerator.getStreetField().getText());
		customParameterList.put(UserParameters.HOME_NO ,customerTableGenerator.getHomeNrField().getText());
		customParameterList.put(UserParameters.POSTCODE ,customerTableGenerator.getPostCode().getText());
		customParameterList.put(UserParameters.CITY ,customerTableGenerator.getCity().getText());
		customParameterList.put(UserParameters.COUNTRY ,customerTableGenerator.getCountry().getText());
		customParameterList.put(UserParameters.PHONE ,customerTableGenerator.getPhoneField().getText());
		customParameterList.put(UserParameters.MOBILE_PHONE ,customerTableGenerator.getMobilePhoneField().getText());
		customParameterList.put(UserParameters.FAX ,customerTableGenerator.getFaxField().getText());
		customParameterList.put(UserParameters.EMAIL ,customerTableGenerator.getEmailField().getText());
		customParameterList.put(UserParameters.BIRTHDAY ,customerTableGenerator.getBirthdayField().getText());
		customParameterList.put(UserParameters.BANK_NO ,customerTableGenerator.getBankNoField().getText());
		customParameterList.put(UserParameters.BLZ ,customerTableGenerator.getBlzField().getText());
		customParameterList.put(UserParameters.IBAN ,customerTableGenerator.getIbanField().getText());
		customParameterList.put(UserParameters.BIC_NO ,customerTableGenerator.getBicNoField().getText());
		
		customController.transformAndPersist(customParameterList);
	}
	
	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}
	
	/**
	 * Hier wird die Tabelle, in der die Kunden gelistet und gesucht werden kann, erzugt und in CustomerArea hinzugefügt
	 */
	private void addCustomerSelectionArea() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonTable.fxml"));
		try {
			AnchorPane customerSelectionArea = (AnchorPane) loader.load();
			stackPane.getChildren().add(customerSelectionArea);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
