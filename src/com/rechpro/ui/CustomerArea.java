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

	private VBoxGenerator vBoxGenerator;
	private Stage customerStage;
	private static Button customerSaveBtn;
	private static Button customerCancelBtn;
	final HBox hb = new HBox();
	StackPane stackPane = new StackPane();
	
	private CustomerController customController;
	
	public CustomerArea() {
		customController = new CustomerController();
		vBoxGenerator = new VBoxGenerator();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonTable.fxml"));
		try {
			AnchorPane page = (AnchorPane) loader.load();
			stackPane.getChildren().add(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected VBox getTableViewKunde() {
		final VBox customerArea = new VBox(2);

		// Button and Text to create new customer
		VBox createNewCustomerBtnAndText = new VBox(2);
		Button createNewCustomerBtn = getNewCustomerCreatButtonWithText();
		Text createNewCustomerText = new Text("Erstelle neue Kunde");
		createNewCustomerBtnAndText.getChildren().addAll(createNewCustomerBtn, createNewCustomerText);
		
		// new window if createNewCustomerButton is clicked
		customerStage = new Stage();
		VBox customCreateWindow = vBoxGenerator.createVBox();
		customerStage.setScene(new Scene(customCreateWindow));
		createNewCustomerBtn.setOnAction(event->customerStage.show());
		customerSaveBtn = vBoxGenerator.getCustomerSaveButton();
		customerCancelBtn = vBoxGenerator.getCustomerCancelButton();
		customerSaveBtn.setOnAction(event->checkMandatoryFieldAndSaveCustomer());
		customerCancelBtn.setOnAction(event->customerStage.close());

		customerArea.setSpacing(5);
		customerArea.setPadding(new Insets(10, 0, 0, 10));
		customerArea.getChildren().addAll(stackPane,createNewCustomerBtnAndText);
		return customerArea;
	}

	private void checkMandatoryFieldAndSaveCustomer() {
		if(vBoxGenerator.areMandatoryInputsDone()){
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
		
		customController.transformAndPersist(customParameterList);
	}
	
	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}
}
