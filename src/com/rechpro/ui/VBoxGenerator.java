package com.rechpro.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class VBoxGenerator {

	protected static final String MISSED_REQUIRED_FIELD = "Obligatorische Felder sind nicht eingegeben!";
	protected static final String INVALID_TEXTFIELD_CSS = "-fx-border-color: red;";
	protected static final String VALID_TEXTFIELD_CSS = "-fx-border-color: #DCDCDC;";
	protected static final String BUTTON_STYLE = "-fx-font: 5 arial; -fx-base: #b6e7c9;";
	protected static final String LABEL_FONT = "Roboto";
	protected static final int VALUE_COLUMN_SPACING = 3;
	protected static final int COLUMN_SPACING = 10;
	protected static final int VBOX_SPACING = 10;
	protected static final int COLUMN_TEXT_SIZE = 15;
	protected static final int SPACE_BETWEEN_BUTTONS = 20;
	protected static final String SAVE_BTN_LABEL = "Speichern";
	protected static final String CANCEL_BTN_LABEL = "Abbrechen";

	
	protected Button saveButton;
	protected Button getSaveButton() {
		return saveButton;
	}
	protected void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	protected Button cancelButton;
	protected Button getCancelButton() {
		return cancelButton;
	}
	protected void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}


	static ImageView createImageView(Class classType, String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(classType.getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}

	protected void setTextFontAndSize(VBox column, String font, int textSize) {
		for (int i = 0; i < column.getChildren().size(); i++) {
			Text text = (Text) column.getChildren().get(i);
			text.setFont(Font.font(font, textSize));
		}
	}
	
	protected void resetInputValues(VBox createWindow) {
		Object hBoxObject = createWindow.getChildren().get(1);
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
						((ChoiceBox) columnObject).setValue(null);
					}
				}
			}
		}
		setInfoMsg(createWindow, "");
	}
	
	protected void setInfoMsg(VBox createWindow, String msg) {
		Object windowObject = createWindow.getChildren().get(2);
    	if (windowObject instanceof HBox) {
    		HBox infoMsgBox = (HBox) windowObject;
    		Object infoObject = infoMsgBox.getChildren().get(0);
        	if (infoObject instanceof Text) {
        		Text infoMsg = (Text) infoObject;
        		infoMsg.setText(msg);
        	}
    	}
	}
	
	abstract boolean areMandatoryInputsDone();

}
