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

		Scene scene = null;
		try {
			// Menuleiste
			MenuLeiste menuLeiste = new MenuLeiste();
			MenuBar menuBar = menuLeiste.getMenu();

			// LefArea
			LeftArea leftArea = new LeftArea();
			HBox hBoxLeftArea = leftArea.getLeftArea();

			// RightArea
			RightArea rightArea = new RightArea();
			HBox hBoxRightArea = rightArea.getRightHBox();

			// CenterPane
<<<<<<< Updated upstream:src/com/rechpro/ui/Launcher.java
			CenterArea centerArea = new CenterArea();
			StackPane paneCenterArea = centerArea.getCenterPane();
=======
			centerArea = new CenterArea();
			paneCenterArea = centerArea.getCenterPane(EnumButton.WILLKOMMEN);
>>>>>>> Stashed changes:src/com/rechpro/guindi/Launcher.java

			// Footer
			Footer footer = new Footer();
			VBox vBoxFooter = footer.getFooter();

			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("RechProjFXML.fxml"));

			root.setTop(menuBar);
			root.setRight(hBoxRightArea);
			root.setBottom(vBoxFooter);
			root.setLeft(hBoxLeftArea);
			root.setCenter(paneCenterArea);

			scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("BorderPane Example");
			primaryStage.setScene(scene);

			// TODO : here close previous window before open new
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}