package com.rechpro.guindi;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

			Button btnKunden = new Button();
			btnKunden.setGraphic(createImageView("../img/custom.png", 80, 80));

			Button btnRechnung = new Button();
			btnRechnung.setGraphic(createImageView("../img/rechnung.jpg", 80, 80));

			Button btnWaren = new Button();
			btnWaren.setGraphic(createImageView("../img/ware.jpg", 80, 80));

			Button btnEinstellung = new Button();
			btnEinstellung.setGraphic(createImageView("../img/setting.png", 80, 80));

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
