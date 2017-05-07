package com.rechpro.ui;

import java.io.IOException;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.rechpro.appcontext.ApplicationContextProvider;
import com.rechpro.ui.area.ButtonOnLeftArea;
import com.rechpro.ui.area.CenterArea;
import com.rechpro.ui.area.LeftArea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import resources.PathClass;

public class Launcher extends Application {
	public static BorderPane root;
	
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
			//RightArea rightArea = new RightArea();
			CenterArea centerArea = new CenterArea();
			Footer footer = new Footer();

			root = (BorderPane) FXMLLoader.load(getClass().getResource(PathClass.RECHPRO_JFXML_FILE));
		       
			/**
			 * TODO HME Temporary outer Function, maybe coming later
			 */
			//rightArea.getRightArea(root);
			menuLeiste.getMenu(root);
			footer.getFooter(root);
			leftArea.createLeftArea(root);
			root.setCenter(centerArea.loadCenterPane(ButtonOnLeftArea.KUNDEN, null));

			scene = new Scene(root, 900, 500);
			scene.getStylesheets().add(getClass().getResource(PathClass.APPLICATION_CSS).toExternalForm());
			primaryStage.setTitle("RechPro");
			primaryStage.setScene(scene);

			// TODO KDO: here close previous window before open new
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(
				"conf/rechpro-applicationcontext.xml");
		ApplicationContextProvider acProvider = new ApplicationContextProvider();
		acProvider.setApplicationContext(context);
		launch(args);
	}
}