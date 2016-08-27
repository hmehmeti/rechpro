package com.rechpro.ui;

import com.rechpro.persistence.Address;
import com.rechpro.persistence.Customer;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author kdogan
 * @email kamuran1905@yahoo.de
 **/
public class RechnungArea {

	private Address address = new Address("Goetherstr", "23",  "73888", "Stuttgart", "Deutschland");
	private Customer customer = new Customer("Hasan", "Mehmeti", "hasan.mehmeti@gmail.com", address);
	public RechnungArea() {

		customer.setTelefonNumber("017631282828");
	}

	

	public GridPane addGridPane() {

		GridPane grid = new GridPane();
		BorderPane mainWinBorderPane = new BorderPane();
		mainWinBorderPane.setPrefSize(700,1000);
		
		VBox mainWindow = new VBox(20);
		// ------------Header begin---------------------------//
		BorderPane header = new BorderPane();
		header.setPrefSize(700, 150);
		ImageView logo = createImageView("../img/ware.png", 80, 80);
		header.setRight(logo);
		mainWinBorderPane.setTop(header);
		// ------------Header end ----------------------------//

		// ------------Adress of seller ----------------------//
		BorderPane center = new BorderPane();
		center.setPrefSize(700, 400);
		BorderPane centerTop = new BorderPane();
		centerTop.setPrefHeight(100);
		VBox miniAdress = new VBox(3);
		Text sellerAddress = new Text("Körnerstr. 24 78777 Karlsruhe");
		sellerAddress.setFont(Font.font("Verdana", 8));
		Line line = new Line(90, 40, 350, 40);
	    line.setStroke(Color.BLACK);
		
		miniAdress.getChildren().addAll(sellerAddress, line);
		centerTop.setTop(miniAdress);
		// ------------Adress of seller end-------------------//

		// ------- third row address of customer (left) date and id of bill (right) -----//
		VBox customAndAddress = new VBox(5);
		String customName = customer.getFirstName() + customer.getLastName();
		Text custumName = new Text(customName);
		//TODO: hier muss die Adresse nachdem Hausnummer in zweite Zeile geschrieben werden
		String street = customer.getAddress().getStreet().getValue();
		String number = customer.getAddress().getNumber().getValue();
		String postCode = customer.getAddress().getPostCode().getValue();
		String city = customer.getAddress().getCity().getValue();
		
		Text custumAdress = new Text(street+" " + number + "\n" + postCode+" "+city);
		customAndAddress.getChildren().addAll(custumName, custumAdress);
		centerTop.setLeft(customAndAddress);
		
		VBox date = new VBox(5);
		Text dateText = new Text("Datum: 12.12.2014");
		
		Text customNr = new Text("KundenNr.: " + customer.getId());
		Text billId = new Text("RechungsNr.: 1234567887");
		date.getChildren().addAll(dateText, customNr, billId);

		centerTop.setRight(date);
		//customAndDate.getChildren().addAll(customAndAddress, date);

		// ------- third row address of customer (left) date and id of bill (right) end-----//

		// ------ text Rechung --------------------------------//
		VBox billHBox = new VBox();
		Label billText = new Label("RECHNUNG");
		billText.setFont(new Font("Arial", 30));
		Line line4 = new Line(90, 40, 800, 40);
	    line4.setStroke(Color.BLACK);
		billHBox.getChildren().addAll(billText,line4);
		centerTop.setBottom(billHBox);
		// ------ text Rechung end----------------------------//

		// -------- list of items -----------------------------//
		TableView table = new TableView();
		BorderPane centerButtom = new BorderPane();
		TableColumn<Customer,String> item = new TableColumn<Customer,String>("Artikel");
		item.prefWidthProperty().bind(table.widthProperty().multiply(0.50));
        TableColumn<Customer,Integer> numberOfItem = new TableColumn<Customer,Integer>("Anzahl");
        numberOfItem.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        TableColumn<Customer,Double> onePrise = new TableColumn<Customer,Double>("Einzel Preis");
        onePrise.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        TableColumn<Customer,Double> entirePrise = new TableColumn<Customer,Double>("Gesamt Preis");
        entirePrise.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        table.getColumns().addAll(item, numberOfItem, onePrise, entirePrise);

		centerButtom.setCenter(table);
		
	//	midlePane.getChildren().addAll(miniAdress, customAndDate, warenList);
		center.setTop(centerTop);
		center.setCenter(centerButtom);
		mainWinBorderPane.setCenter(center);
		// -------- list of items end ------------------------//

		// --------- footer ----------------------------------//
		VBox footer = new VBox(5);
		footer.setPrefWidth(700);

		Text footerInfo = new Text("Füß Informationen");
		Line line2 = new Line(90, 40, 800, 40);
	    line2.setStroke(Color.BLACK);
	    Line line3 = new Line(90, 40, 800, 40);
	    line3.setStroke(Color.BLACK);

		footer.getChildren().addAll(line2, footerInfo, line3);

		mainWinBorderPane.setBottom(footer);
		// --------- footer end ------------------------------//
	
		mainWindow.getChildren().addAll(mainWinBorderPane);
		grid.getChildren().add(mainWindow);

		return grid;
	}

	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}
}
