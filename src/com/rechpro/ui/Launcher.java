package com.rechpro.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Launcher extends Application {
	BorderPane root;

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
			MenuLeiste menuLeiste = new MenuLeiste();
			LeftArea leftArea = new LeftArea();
			RightArea rightArea = new RightArea();
			CenterArea centerArea = new CenterArea();
			Footer footer = new Footer();

			root = (BorderPane) FXMLLoader.load(getClass().getResource("RechProjFXML.fxml"));

			/**
			 * TODO HME Temporary outer Function, maybe coming later
			 */
			//rightArea.getRightArea(root);
			menuLeiste.getMenu(root);
			footer.getFooter(root);
			leftArea.getLeftArea(root);
			centerArea.loadCenterPane(EnumButton.WILLKOMMEN);

			scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("BorderPane Example");
			primaryStage.setScene(scene);

			// TODO KDO: here close previous window before open new
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}