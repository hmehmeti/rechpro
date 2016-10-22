package com.rechpro.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import com.rechpro.viewmodel.Address;
import com.rechpro.viewmodel.CustomerViewModel;
import com.rechpro.worker.TestCustomers;

/**
 * @author kdogan
 * @email kamuran1905@yahoo.de
 **/
public class RechnungArea {

	private CustomerViewModel CustomerViewModel = new TestCustomers().getUser1();
	public RechnungArea() {

		//CustomerViewModel.setTelefonNumber("017631282828");
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
		Text sellerAddress = new Text("K�rnerstr. 24 78777 Karlsruhe");
		sellerAddress.setFont(Font.font("Verdana", 8));
		Line line = new Line(90, 40, 350, 40);
	    line.setStroke(Color.BLACK);

		miniAdress.getChildren().addAll(sellerAddress, line);
		centerTop.setTop(miniAdress);
		// ------------Adress of seller end-------------------//

		// ------- third row address of CustomerViewModel (left) date and id of bill (right) -----//
		VBox customAndAddress = new VBox(5);
		String customName = CustomerViewModel.getFirstName().get() + CustomerViewModel.getLastName().get();
		Text custumName = new Text(customName);
		//TODO: hier muss die Adresse nachdem Hausnummer in zweite Zeile geschrieben werden
		String street = CustomerViewModel.getStreet().get();
		String number = CustomerViewModel.getNo().get();
		String postCode = CustomerViewModel.getPostCode().get();
		String city = CustomerViewModel.getCity().get();

		Text custumAdress = new Text(street+" " + number + "\n" + postCode+" "+city);
		customAndAddress.getChildren().addAll(custumName, custumAdress);
		centerTop.setLeft(customAndAddress);

		VBox date = new VBox(5);
		Text dateText = new Text("Datum: 12.12.2014");

		Text customNr = new Text("KundenNr.: " + CustomerViewModel.getCustomerId().get());
		Text billId = new Text("RechungsNr.: 1234567887");
		date.getChildren().addAll(dateText, customNr, billId);

		centerTop.setRight(date);
		//customAndDate.getChildren().addAll(customAndAddress, date);

		// ------- third row address of CustomerViewModel (left) date and id of bill (right) end-----//

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
		TableColumn<CustomerViewModel,String> item = new TableColumn<CustomerViewModel,String>("Artikel");
		item.prefWidthProperty().bind(table.widthProperty().multiply(0.50));
        TableColumn<CustomerViewModel,Integer> numberOfItem = new TableColumn<CustomerViewModel,Integer>("Anzahl");
        numberOfItem.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        TableColumn<CustomerViewModel,Double> onePrise = new TableColumn<CustomerViewModel,Double>("Einzel Preis");
        onePrise.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        TableColumn<CustomerViewModel,Double> entirePrise = new TableColumn<CustomerViewModel,Double>("Gesamt Preis");
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

		Text footerInfo = new Text("F�� Informationen");
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
