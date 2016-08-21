package com.rechpro.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CenterArea {

	protected StackPane loadCenterPane(EnumButton button) {
		StackPane stackPane = null;

		Rectangle rec = null;

		switch (button) {
		case RECHNUNG:
			stackPane = new StackPane();
			stackPane.setAlignment(Pos.CENTER);
			rec = new Rectangle();
			rec.setFill(Color.BLACK);
			rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
			rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
			stackPane.getChildren().addAll(rec);

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
			stackPane.getChildren().addAll(new KundenArea().getTableViewKunde());

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
