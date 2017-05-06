package com.rechpro.ui;

import javafx.scene.layout.VBox;

public abstract class ElementArea {

	protected static final int ELEMENT_AREA_VBOX_SPACING = 5;
	protected static final int BUTTON_WIDTH_AND_HEIGHT = 20;
	protected static final int VBOX_SPACING = 2;
	
	abstract void checkMandatoryFieldsAndSave(VBox elementCreateWindow);
}
