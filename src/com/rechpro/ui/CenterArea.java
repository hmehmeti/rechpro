package com.rechpro.ui;

import java.io.IOException;

import com.rechpro.worker.CustomController;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CenterArea {

	protected StackPane loadCenterPane(EnumButton button) {
		StackPane stackPane = null;

		Rectangle rec = null;

		switch (button) {
		case RECHNUNG:
			stackPane = new StackPane();
			stackPane.setAlignment(Pos.CENTER);
			rec = new Rectangle();
			rec.setFill(Color.WHITE);
			stackPane.getChildren().addAll(new RechnungArea().addGridPane());
//			rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
//			rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
//			stackPane.getChildren().addAll(rec);

			break;
		case EINSTELLUNG:
			stackPane = new StackPane();
			stackPane.setAlignment(Pos.CENTER);
			rec = new Rectangle();
			rec.setFill(Color.BROWN);
			rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
			rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
			stackPane.getChildren().addAll(rec);

			break;
		case KUNDEN:
			stackPane = new StackPane();
			stackPane.setAlignment(Pos.CENTER);
			//stackPane.getChildren().addAll(new KundenArea().getTableViewKunde());
			
			try {
				AnchorPane customerTableView = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/customTable.fxml"));
				stackPane.getChildren().addAll(customerTableView);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			break;
		default:
			stackPane = new StackPane();
			stackPane.setAlignment(Pos.CENTER);
			rec = new Rectangle();
			rec.setFill(Color.DODGERBLUE);
			rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
			rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
			stackPane.getChildren().addAll(rec);

			break;
		}

		return stackPane;
	}

}
