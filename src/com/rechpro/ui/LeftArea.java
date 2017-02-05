package com.rechpro.ui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import resources.PathClass;

public class LeftArea {

	public void createLeftArea(BorderPane root) {
		HBox hbox = new HBox();

		String cssFont = "-fx-font: 5 arial; -fx-base: #b6e7c9;";
		int buttonWidth = 60;
		int buttonHight = 60;

		try {
			VBox vbox = new VBox(10);
			vbox.setPadding(new Insets(10));
			Text text = new Text("Navigation");
			text.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));

			Button btnKunden = new Button();
			btnKunden.setGraphic(createImageView(PathClass.CUSTOMER_ICON_PATH, buttonWidth, buttonHight));
			btnKunden.setStyle(cssFont);
			btnKunden.setOnAction(event->root.setCenter(new CenterArea().loadCenterPane(ButtonOnLeftArea.KUNDEN, null)));

			Button btnRechnung = new Button();
			btnRechnung.setGraphic(createImageView(PathClass.RECHNUNG_ICON_PATH, buttonWidth, buttonHight));
			btnRechnung.setStyle(cssFont);
			btnRechnung.setOnAction(event->root.setCenter(new CenterArea().loadCenterPane(ButtonOnLeftArea.RECHNUNG, null)));

			Button btnWaren = new Button();
			btnWaren.setGraphic(createImageView(PathClass.ARTICLE_ICON_PATH, buttonWidth, buttonHight));
			btnWaren.setStyle(cssFont);
			btnWaren.setOnAction(event->root.setCenter(new CenterArea().loadCenterPane(ButtonOnLeftArea.WAREN, null)));
			

			Button btnEinstellung = new Button();
			btnEinstellung.setGraphic(createImageView(PathClass.SETTING_ICON_PATH, buttonWidth, buttonHight));
			btnEinstellung.setStyle(cssFont);
			//TODO: implement EINSTELLUNG
			//btnEinstellung.setOnAction(event->root.setCenter(new CenterArea().loadCenterPane(EnumButton.EINSTELLUNG)));

			vbox.getChildren().addAll(text, btnRechnung, btnWaren, btnKunden, btnEinstellung);
			hbox.getChildren().addAll(vbox, new Separator(Orientation.VERTICAL));
		} catch (Exception e) {
			e.printStackTrace();
		}

		root.setLeft(hbox);
	}

	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}

}
