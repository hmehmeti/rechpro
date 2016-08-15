package com.rechpro.guindi;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LeftArea {

	public HBox getLeftArea() {
		HBox hbox = null;
		VBox vbox = null;
		VBox vboxText = null;

		try {

			hbox = new HBox();
			vbox = new VBox(10);
			vbox.setPadding(new Insets(10));

			Text text = new Text("Navigation");
			text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));

			// Kunden
			Button btnKunden = new Button();
			btnKunden.setText(IFormRechnung.BTN_KUNDEN);

			// Rechnung
			Button btnRechnung = new Button();
			btnRechnung.setText(IFormRechnung.BTN_RECHNUNGEN);

			// Waren
			Button btnWaren = new Button();
			btnWaren.setText(IFormRechnung.BTN_WAREN);

			// Einstellung
			Button btnEinstellung = new Button();
			btnEinstellung.setText(IFormRechnung.BTN_EINSTELLUNGEN);


			vbox.getChildren().addAll(text, btnRechnung, btnWaren, btnKunden, btnEinstellung);
			hbox.getChildren().addAll(vbox, new Separator(Orientation.VERTICAL));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hbox;
	}

}
