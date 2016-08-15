package com.rechpro.guindi;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CenterArea {

	 protected StackPane getCenterPane()
	    {
	        StackPane stackPane = new StackPane();
	        stackPane.setAlignment(Pos.CENTER);
	         
	        Rectangle rec = new Rectangle();
	        rec.setFill(Color.DODGERBLUE);
	        rec.widthProperty().bind(stackPane.widthProperty().subtract(50));
	        rec.heightProperty().bind(stackPane.heightProperty().subtract(50));
	         
	        stackPane.getChildren().addAll(rec);
	         
	        return stackPane;
	    }
	
}
