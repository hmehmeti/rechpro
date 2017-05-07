package com.rechpro.ui.selectionarea;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class ElementSelectionArea {

	protected static final int ELEMENT_AREA_VBOX_SPACING = 5;
	protected static final int BUTTON_WIDTH_AND_HEIGHT = 20;
	protected static final int VBOX_SPACING = 2;
	
	/**
	 * Hier wird die Tabelle, in der die Elementen gelistet und gesucht werden können, erzeugt.
	 * @param selectionTableFXML
	 * 			Name der FXML-Datei inklusiv Datei-Endung .fxml
	 * @param stackPane 
	 */
	void loadSelectionArea(String selectionTableFXML, StackPane stackPane) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(selectionTableFXML));
		try {
			AnchorPane articleSelectionArea = (AnchorPane) loader.load();
			stackPane.getChildren().add(articleSelectionArea);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	abstract void checkMandatoryFieldsAndSave(VBox elementCreateWindow);
	
	abstract void transformAndPersist();
}
