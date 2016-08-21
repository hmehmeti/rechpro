package com.rechpro.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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

			Button btnKunden = new Button();
<<<<<<< Updated upstream:src/com/rechpro/ui/LeftArea.java
			btnKunden.setGraphic(createImageView("../img/custom.png", 80, 80));
=======
			btnKunden.setText(IFormRechnung.BTN_KUNDEN);
			btnKunden.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
			btnKunden.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	new CenterArea().getCenterPane(EnumButton.KUNDEN);
		            }
		        });
>>>>>>> Stashed changes:src/com/rechpro/guindi/LeftArea.java

			Button btnRechnung = new Button();
<<<<<<< Updated upstream:src/com/rechpro/ui/LeftArea.java
			btnRechnung.setGraphic(createImageView("../img/rechnung.jpg", 80, 80));
=======
			btnRechnung.setText(IFormRechnung.BTN_RECHNUNGEN);
			btnRechnung.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");

>>>>>>> Stashed changes:src/com/rechpro/guindi/LeftArea.java

			Button btnWaren = new Button();
<<<<<<< Updated upstream:src/com/rechpro/ui/LeftArea.java
			btnWaren.setGraphic(createImageView("../img/ware.jpg", 80, 80));
=======
			btnWaren.setText(IFormRechnung.BTN_WAREN);
			btnWaren.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
>>>>>>> Stashed changes:src/com/rechpro/guindi/LeftArea.java

			Button btnEinstellung = new Button();
<<<<<<< Updated upstream:src/com/rechpro/ui/LeftArea.java
			btnEinstellung.setGraphic(createImageView("../img/setting.png", 80, 80));
=======
			btnEinstellung.setText(IFormRechnung.BTN_EINSTELLUNGEN);
			btnEinstellung.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");

>>>>>>> Stashed changes:src/com/rechpro/guindi/LeftArea.java

			vbox.getChildren().addAll(text, btnRechnung, btnWaren, btnKunden, btnEinstellung);
			hbox.getChildren().addAll(vbox, new Separator(Orientation.VERTICAL));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hbox;
	}

	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}

}
