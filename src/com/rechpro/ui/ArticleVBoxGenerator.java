package com.rechpro.ui;

import org.apache.commons.lang3.StringUtils;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ArticleVBoxGenerator extends VBoxGenerator {
	
	private static final String CREATE_ARTICLE_LABEL = "Neuer Artikel erstellen";
	private static final String[] ARTICLE_CATEGORIES = {"hardware", "software"};
	
	private TextField articleNumber;
	private TextField articleName;
	private TextField articleDescription;
	private TextField articlePrice;
	private ChoiceBox<String> articleCategory;
	
	public ArticleVBoxGenerator() {
		initialiseArticleFields();
		setTextStyleToValid();
	}
	
	private void initialiseArticleFields() {
		articleNumber = new TextField();
		articleName = new TextField();
		articleDescription = new TextField();
		articlePrice = new TextField();
		articleCategory = new ChoiceBox<String>(FXCollections.observableArrayList(ARTICLE_CATEGORIES));
		
	}
	
	protected void setTextStyleToValid() {
		articleNumber.setStyle(VALID_TEXTFIELD_CSS);
		articleName.setStyle(VALID_TEXTFIELD_CSS);
		articlePrice.setStyle(VALID_TEXTFIELD_CSS);
		articleCategory.setStyle(VALID_TEXTFIELD_CSS);
	}

	public VBox createAddingNewArticleTable() {
		window = new VBox(VBOX_SPACING);
		HBox mainWindow = new HBox();
		window.setMargin(mainWindow, new Insets(10, 10, 10, 10));
		
		Label articleData = new Label(CREATE_ARTICLE_LABEL);
		articleData.setFont(Font.font("Roboto", 20));
		window.setMargin(articleData, new Insets(10, 10, 10, 10));
		
		VBox firstColumn = new VBox(COLUMN_SPACING);
		Text name = new Text("Article name");
		Text number = new Text("Article number");
		Text category = new Text("Article category");
		Text description = new Text("Article description");
		Text price = new Text("Article price");
		firstColumn.getChildren().addAll(name, number, price, category, description);
		setSize(firstColumn, COLUMN_TEXT_SIZE);
		
		VBox secondColumn = new VBox(COLUMN_SPACING);
		for (int i = 0; i < firstColumn.getChildren().size(); i++) {
			secondColumn.getChildren().add(new Text(" : "));
		}
		setSize(secondColumn, COLUMN_TEXT_SIZE);
		
		VBox thirdColumn = new VBox(VALUE_COLUMN_SPACING);
		thirdColumn.getChildren().addAll(articleName, articleNumber, articlePrice, 
				articleCategory, articleDescription);
		
		mainWindow.getChildren().addAll(firstColumn, secondColumn, thirdColumn);
		
		HBox infoMsgBox = new HBox();
		Text infoMsg = new Text();
		infoMsg.setFill(Color.RED);
		infoMsgBox.getChildren().add(infoMsg);
		window.setMargin(infoMsgBox, new Insets(10, 10, 10, 10));
		
		HBox buttons = new HBox(20);
		cancelBtn = new Button("Abbrechen");
		saveBtn = new Button("Speichern");
		buttons.getChildren().addAll(cancelBtn, saveBtn);		
		window.setMargin(buttons, new Insets(10, 10, 10, 10));
		
		window.getChildren().addAll(articleData, mainWindow, infoMsgBox, buttons);
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
						((ChoiceBox) columnObject).setValue(new String(""));
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
	
	@Override
	boolean areMandatoryInputsDone() {
		boolean result = true;
		if (StringUtils.isEmpty(articleName.getText())) {
			articleName.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if (StringUtils.isEmpty(articleNumber.getText())) {
			articleNumber.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if (StringUtils.isEmpty(articlePrice.getText())) {
			articlePrice.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		if (articleCategory.getSelectionModel().isEmpty()) {
			articleCategory.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		return result;
	}
	
	public TextField getArticleNumber() {
		return articleNumber;
	}

	public TextField getArticleName() {
		return articleName;
	}

	public TextField getArticleDescription() {
		return articleDescription;
	}

	public TextField getArticlePrice() {
		return articlePrice;
	}

}
