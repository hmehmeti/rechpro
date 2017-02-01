package com.rechpro.ui;

import java.util.Random;

import com.rechpro.viewmodel.CustomerViewModel;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CenterArea {

	public StackPane loadCenterPane(ButtonOnLeftArea button, CustomerViewModel customerToCreateBill) {
		StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.CENTER);
		Rectangle rec = new Rectangle();

		switch (button) {
		case RECHNUNG:
			rec.setFill(Color.WHITE);
			stackPane.getChildren().addAll(new RechnungArea(customerToCreateBill).addGridPane());
			break;
			
		case EINSTELLUNG:
			rec.setFill(Color.BROWN);
			rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
			rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
			stackPane.getChildren().addAll(rec);
			break;
			
		case KUNDEN:
			stackPane.getChildren().add(new CustomerArea().getTableViewKunde());
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
