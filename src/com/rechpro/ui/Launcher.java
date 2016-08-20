package com.rechpro.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			getKern(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void getKern(Stage primaryStage) {
		BorderPane root;

		MenuLeiste menuLeiste = null;
		MenuBar menuBar = null;

		LeftArea leftArea = null;
		HBox hBoxLeftArea = null;

		RightArea rightArea = null;
		HBox hBoxRightArea = null;

		CenterArea centerArea = null;
		StackPane paneCenterArea = null;

		Footer footer = null;
		VBox vBoxFooter = null;

		Scene scene = null;
		try {
			// Menuleiste
			menuLeiste = new MenuLeiste();
			menuBar = menuLeiste.getMenu();

			// LefArea
			leftArea = new LeftArea();
			hBoxLeftArea = leftArea.getLeftArea();

			// RightArea
			rightArea = new RightArea();
			hBoxRightArea = rightArea.getRightHBox();

			// CenterPane
			centerArea = new CenterArea();
			paneCenterArea = centerArea.getCenterPane();

			// Footer
			footer = new Footer();
			vBoxFooter = footer.getFooter();

			root = (BorderPane) FXMLLoader.load(getClass().getResource("RechProjFXML.fxml"));

			root.setTop(menuBar);
			root.setRight(hBoxRightArea);
			root.setBottom(vBoxFooter);
			root.setLeft(hBoxLeftArea);
			root.setCenter(paneCenterArea);

			scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("BorderPane Example");
			primaryStage.setScene(scene);
			
			//TODO : here close previous window before open new
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}