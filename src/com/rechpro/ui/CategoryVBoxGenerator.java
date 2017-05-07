package com.rechpro.ui;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.rechpro.persistence.ICategoryDBService;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CategoryVBoxGenerator extends VBoxGenerator {
	
	private static final String CATEGORY_DESCRIPTION_TEXT = "Beschreibung";
	private static final String CATEGORY_NAME_TEXT = "Name*";
	private static final String CREATE_CATEGORY_LABEL = "Neue Kategorie erstellen";
	public static final String CATEGORY_NAME_EXISTS =  "Eingegebene Kategorie existiert schon. \nBitte eine neue eingeben!";
	
	private TextField categoryName;
	public TextField getCategoryName() {
		return categoryName;
	}

	private TextField categoryDescription;
	public TextField getCategoryDescription() {
		return categoryDescription;
	}
	
	public CategoryVBoxGenerator() {
		initialiseCategoryFields();
		setTextStyleToValid();
	}

	private void initialiseCategoryFields() {
		categoryName = new TextField();
		categoryDescription = new TextField();
	}

	public VBox createAddingNewCategoryTable() {
		VBox window = new VBox(VBOX_SPACING);
		
		HBox mainWindow = new HBox();
		VBox.setMargin(mainWindow, new Insets(10, 10, 10, 10));
		
		Label categoryDataLabel = new Label(CREATE_CATEGORY_LABEL);
		categoryDataLabel.setFont(Font.font(LABEL_FONT, 20));
		VBox.setMargin(categoryDataLabel, new Insets(10, 10, 10, 10));
		
		VBox firstColumn = new VBox(COLUMN_SPACING);
		Text name = new Text(CATEGORY_NAME_TEXT);
		Text description = new Text(CATEGORY_DESCRIPTION_TEXT);
		firstColumn.getChildren().addAll(name, description);
		setTextFontAndSize(firstColumn, LABEL_FONT, COLUMN_TEXT_SIZE);
		
		VBox secondColumn = new VBox(COLUMN_SPACING);
		for (int i = 0; i < firstColumn.getChildren().size(); i++) {
			secondColumn.getChildren().add(new Text(" : "));
		}
		setTextFontAndSize(secondColumn,LABEL_FONT, COLUMN_TEXT_SIZE);
		
		VBox thirdColumn = new VBox(COLUMN_SPACING);
		thirdColumn.getChildren().addAll(categoryName, categoryDescription);

		mainWindow.getChildren().addAll(firstColumn, secondColumn, thirdColumn);
		
		HBox infoMsgBox = new HBox();
		Text infoMsg = new Text();
		infoMsg.setFill(Color.RED);
		infoMsgBox.getChildren().add(infoMsg);
		VBox.setMargin(infoMsgBox, new Insets(10, 10, 10, 10));
		
		HBox buttons = new HBox(SPACE_BETWEEN_BUTTONS);
		Button cancelBtn = new Button(CANCEL_BTN_LABEL);
		setCancelButton(cancelBtn);
		Button saveBtn = new Button(SAVE_BTN_LABEL);
		setSaveButton(saveBtn);
		buttons.getChildren().addAll(saveBtn, cancelBtn);		
		VBox.setMargin(buttons, new Insets(10, 10, 10, 10));
		
		window.getChildren().addAll(categoryDataLabel, mainWindow, infoMsgBox, buttons);
		
		return window;
	}
	
	protected void setTextStyleToValid() {
		categoryName.setStyle(VALID_TEXTFIELD_CSS);
		categoryDescription.setStyle(VALID_TEXTFIELD_CSS);
	}
	
	@Override
	public boolean areMandatoryInputsDone() {
		boolean result = true;
		if (StringUtils.isEmpty(categoryName.getText())) {
			categoryName.setStyle(INVALID_TEXTFIELD_CSS);
			result = false;
		}
		return result;
	}

}
