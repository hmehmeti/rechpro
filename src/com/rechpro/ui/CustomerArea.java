package com.rechpro.ui;

import java.io.IOException;
import java.util.HashMap;

import com.rechpro.viewmodel.CustomerViewModel;
import com.rechpro.worker.CustomerController;
import com.rechpro.worker.UserParameters;
import com.rechpro.worker.VBoxGenerator;

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

	private static final String KEINE_OBLIGATORISCHE_FELDER = "Obligatorische Felder sind nicht eingegeben";
	private VBoxGenerator customerTableGenerator;
	private Stage customerStage;
	private static Button customerSaveBtn;
	private static Button customerCancelBtn;
	final HBox hb = new HBox();
	private StackPane stackPane = new StackPane();
	private CustomerController customController;
	public static TableView<CustomerViewModel> customerTable;
	
	public CustomerArea() {
		customController = new CustomerController();
		customerTableGenerator = new VBoxGenerator();
		loadCustomerSelectionArea();
		
		//stackPane.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
	}
	
	protected VBox getTableViewKunde() {
		VBox customerArea = new VBox(2);
		// Button and Text to create new customer
		VBox createNewCustomerBtnAndText = new VBox(2);
		Button createNewCustomerBtn = getNewCustomerCreatButtonWithText();
		createNewCustomerBtnAndText.getChildren().addAll(createNewCustomerBtn, new Text("Erstelle neue Kunde"));
		
		// new window if createNewCustomerButton is clicked
		customerStage = new Stage();
		VBox customerCreateWindow = customerTableGenerator.createCustomerCreationTable();
		customerStage.setScene(new Scene(customerCreateWindow));
		createNewCustomerBtn.setOnAction(event-> {
			resetInputValues(customerCreateWindow);
			customerStage.show();
		});
		customerSaveBtn = customerTableGenerator.getCustomerSaveButton();
		customerCancelBtn = customerTableGenerator.getCustomerCancelButton();
		customerSaveBtn.setOnAction(event-> {
			checkMandatoryFieldsAndSaveCustomer(customerCreateWindow);
		});
		customerCancelBtn.setOnAction(event->customerStage.close());

		customerArea.setSpacing(5);
		customerArea.setPadding(new Insets(10, 0, 0, 10));
		customerArea.getChildren().addAll(stackPane, createNewCustomerBtnAndText);
		return customerArea;
	}

	private void resetInputValues(VBox customerCreateWindow) {
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
	}

	private void checkMandatoryFieldsAndSaveCustomer(VBox customerCreateWindow) {
		if(customerTableGenerator.areMandatoryInputsDone()){
       	 transformAndPersist();
       	 loadCustomerSelectionArea();
       	 customerStage.close();
        } else {
        	Object windowObject = customerCreateWindow.getChildren().get(2);
        	if (windowObject instanceof Text) {
        		Text infoMsg = (Text) windowObject;
        		infoMsg.setText(KEINE_OBLIGATORISCHE_FELDER);
        	}
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
