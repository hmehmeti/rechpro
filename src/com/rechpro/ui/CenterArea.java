package com.rechpro.ui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CenterArea {

	protected StackPane loadCenterPane(EnumButton button) {
		StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.CENTER);
		Rectangle rec = new Rectangle();

		switch (button) {
		case RECHNUNG:
			rec.setFill(Color.WHITE);
			stackPane.getChildren().addAll(new RechnungArea().addGridPane());
			// rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
			// rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
			// stackPane.getChildren().addAll(rec);

			break;
		case EINSTELLUNG:
			rec.setFill(Color.BROWN);
			rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
			rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
			stackPane.getChildren().addAll(rec);

			break;
		case KUNDEN:
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonTable.fxml"));
			try {
				AnchorPane page = (AnchorPane) loader.load();
				stackPane.getChildren().add(page);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		default:
			rec.setFill(Color.DODGERBLUE);
			rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
			rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
			stackPane.getChildren().addAll(rec);

			break;
		}

		return stackPane;
	}

}
