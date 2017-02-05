package com.rechpro.ui;


import static com.rechpro.ui.VBoxGenerator.window;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class VBoxGenerator {

	protected static final String INVALID_TEXTFIELD_CSS = "-fx-border-color: red;";
	protected static final String VALID_TEXTFIELD_CSS = "-fx-border-color: #DCDCDC;";
	protected static final String BUTTON_STYLE = "-fx-font: 5 arial; -fx-base: #b6e7c9;";
	protected static final int VALUE_COLUMN_SPACING = 3;
	protected static final int COLUMN_SPACING = 10;
	protected static final int VBOX_SPACING = 10;
	protected static final int COLUMN_TEXT_SIZE = 15;
	protected static final int SPACE_BETWEEN_BUTTONS = 20;
	
	static Button saveBtn;
	static Button cancelBtn;
	static VBox window;

	public static ImageView createImageView(Class classType, String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(classType.getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}

	static void setSize(VBox column, int textSize) {
		for (int i = 0; i < column.getChildren().size(); i++) {
			Text text = (Text) column.getChildren().get(i);
			text.setFont(Font.font("Roboto", textSize));
		}
	}
	
	public static VBox getWindow() {
		return window;
	}
	
	public Button getSaveButton(){
		return saveBtn;
	}
	public Button getCancelButton(){
		return cancelBtn;
	}

	
	abstract boolean areMandatoryInputsDone();

	
}
