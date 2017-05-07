package com.rechpro.ui.selectionarea;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public interface IVBoxGenerator {
	
	ImageView createImageView(Class classType, String imgPath, int width, int hight);
	void setTextFontAndSize(VBox column, String font, int textSize);
	void resetInputValues(VBox createWindow);
	void setInfoMsg(VBox createWindow, String msg);
	boolean areMandatoryInputsDone();

}
