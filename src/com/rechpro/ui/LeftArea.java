package com.rechpro.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

	public void getLeftArea(BorderPane root) {
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
			btnKunden.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					root.setCenter(new CenterArea().loadCenterPane(EnumButton.KUNDEN));
				}
			});

			Button btnRechnung = new Button();
			btnRechnung.setGraphic(createImageView(PathClass.RECHNUNG_ICON_PATH, buttonWidth, buttonHight));
			btnRechnung.setStyle(cssFont);
			btnRechnung.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					root.setCenter(new CenterArea().loadCenterPane(EnumButton.RECHNUNG));
				}
			});

			Button btnWaren = new Button();
			btnWaren.setGraphic(createImageView(PathClass.ARTICLE_ICON_PATH, buttonWidth, buttonHight));
			btnWaren.setStyle(cssFont);
			btnWaren.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					// TODO
				}
			});

			Button btnEinstellung = new Button();
			btnEinstellung.setGraphic(createImageView(PathClass.SETTING_ICON_PATH, buttonWidth, buttonHight));
			btnEinstellung.setStyle(cssFont);
			btnEinstellung.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					// TODO
				}
			});

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
