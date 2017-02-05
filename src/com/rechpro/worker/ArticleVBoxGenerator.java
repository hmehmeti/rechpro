package com.rechpro.worker;

import org.apache.commons.lang3.StringUtils;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ArticleVBoxGenerator extends VBoxGenerator {
	
	private static final String CREATE_ARTICLE_LABEL = "Neuer Artikel erstellen";
	
	public ArticleVBoxGenerator() {
		initialiseArticleFields();
	}
	
	private void initialiseArticleFields() {
		articleNumber = new TextField();
		articleName = new TextField();
		articleDescription = new TextField();
		articlePrice = new TextField();
		articleCategory = new ChoiceBox<String>(FXCollections.observableArrayList("hardware", "software"));
		
	}

	public VBox createAddingNewArticleTable() {
		window = new VBox(VBOX_SPACING);
		HBox mainWindow = new HBox();
		window.setMargin(mainWindow, new Insets(10, 10, 10, 10));
		Label articleData = new Label(CREATE_ARTICLE_LABEL);
		articleData.setFont(Font.font("Roboto", 20));
		window.setMargin(articleData, new Insets(10, 10, 10, 10));
		
		VBox firstColumn = new VBox(COLUMN_SPACING);
		Text name = new Text("Article name: ");
		Text number = new Text("Article number: ");
		Text category = new Text("Article category: ");
		Text description = new Text("Article description: ");
		Text price = new Text("Article price: ");
		firstColumn.getChildren().addAll(name, number, price, category, description);
		setSize(firstColumn, COLUMN_TEXT_SIZE);
		
		VBox secondColumn = new VBox(COLUMN_SPACING);
		secondColumn.getChildren().addAll(articleName, articleNumber, articlePrice, 
				articleCategory, articleDescription);
		
		mainWindow.getChildren().addAll(firstColumn, secondColumn);
		
		Text infoMsg = new Text();
		
		HBox buttons = new HBox(20);
		cancelBtn = new Button("Abbrechen");
		saveBtn = new Button("Speichern");
		buttons.getChildren().addAll(cancelBtn, saveBtn);
		
		window.setMargin(buttons, new Insets(10, 10, 10, 10));
		
		window.getChildren().addAll(articleData, mainWindow, infoMsg, buttons);
		return window;
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

	TextField articleNumber;
	TextField articleName;
	TextField articleDescription;
	TextField articlePrice;
	ChoiceBox<String> articleCategory;

	@Override
	boolean areMandatoryInputsDone() {
		if (StringUtils.isEmpty(articleName.getText())
				|| StringUtils.isEmpty(articleNumber.getText())
				|| StringUtils.isEmpty(articlePrice.getText())
				|| articleCategory.getSelectionModel().isEmpty()) {
			return false;
		}
		return true;
	}

}
