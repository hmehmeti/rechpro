package com.rechpro.ui;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author kdogan 
 * @email kamuran1905@yahoo.de
 **/
public class RechnungArea {
	
	public RechnungArea(){
		
	}

	public GridPane addGridPane() {
	
	    GridPane grid = new GridPane();
	    grid.setHgap(50);
	    grid.setVgap(50);
	    grid.setPadding(new Insets(0, 10, 0, 10));

	    // Category in column 2, row 1
	    Text category = new Text("Sales:");
	    category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	    grid.add(category, 1, 0); 

	    // Title in column 3, row 1
	    Text chartTitle = new Text("Current Year");
	    chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	    grid.add(chartTitle, 2, 0);

	    // Subtitle in columns 2-3, row 2
	    Text chartSubtitle = new Text("Goods and Services");
	    grid.add(chartSubtitle, 1, 1, 2, 1);

	    // House icon in column 1, rows 1-2
	    ImageView imageHouse = createImageView("../img/ware.png", 80, 80);
	    grid.add(imageHouse, 0, 0, 1, 2); 

	    // Left label in column 1 (bottom), row 3
	    Text goodsPercent = new Text("Goods\n80%");
	    GridPane.setValignment(goodsPercent, VPos.BOTTOM);
	    grid.add(goodsPercent, 0, 2); 

	    // Chart in columns 2-3, row 3
	    ImageView imageChart = createImageView("../img/rechnung.png", 80, 80);
	    grid.add(imageChart, 1, 2, 2, 1); 

	    // Right label in column 4 (top), row 3
	    Text servicesPercent = new Text("Services\n20%");
	    GridPane.setValignment(servicesPercent, VPos.TOP);
	    grid.add(servicesPercent, 3, 2);

	    return grid;
	}
	
	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}
}
