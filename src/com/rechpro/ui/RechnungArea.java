package com.rechpro.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import resources.PathClass;

import java.util.ArrayList;

import com.rechpro.entity.Article;
import com.rechpro.entity.Customer;
import com.rechpro.persistence.CustomerDBService;
import com.rechpro.transformer.CustomerTransformer;
import com.rechpro.viewmodel.Address;
import com.rechpro.viewmodel.ArticleViewModel;
import com.rechpro.viewmodel.CustomerViewModel;

/**
 * @author kdogan
 * @email kamuran1905@yahoo.de
 **/
public class RechnungArea {
	private CustomerTransformer transformer;
	private CustomerDBService dbService;

	private CustomerViewModel customerViewModel;
	
	private final ObservableList<ArticleViewModel> articles = FXCollections.observableArrayList();
	public RechnungArea() {
		transformer = new CustomerTransformer();
        dbService = new CustomerDBService();
        ArrayList<Customer> customers = (ArrayList<Customer>)dbService.getCustomers();
        customerViewModel = transformer.entityToViewModel(customers.get(0));
	}



	public GridPane addGridPane() {

		GridPane grid = new GridPane();
		BorderPane mainWinBorderPane = new BorderPane();
		mainWinBorderPane.setPrefSize(700,1000);

		VBox mainWindow = new VBox(20);
		// ------------Header begin---------------------------//
		BorderPane header = new BorderPane();
		header.setPrefSize(700, 150);
		ImageView logo = createImageView(PathClass.ARTICLE_ICON_PATH, 80, 80);
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

		// ------- third row address of CustomerViewModel (left) date and id of bill (right) -----//
		VBox customAndAddress = new VBox(5);
		String customName = customerViewModel.getFirstName().get() + customerViewModel.getLastName().get();
		Text custumName = new Text(customName);
		//TODO: hier muss die Adresse nachdem Hausnummer in zweite Zeile geschrieben werden
		String street = customerViewModel.getStreet().get();
		String number = customerViewModel.getNo().get();
		String postCode = customerViewModel.getPostCode().get();
		String city = customerViewModel.getCity().get();

		Text custumAdress = new Text(street+" " + number + "\n" + postCode+" "+city);
		customAndAddress.getChildren().addAll(custumName, custumAdress);
		centerTop.setLeft(customAndAddress);

		VBox date = new VBox(5);
		Text dateText = new Text("Datum: 12.12.2014");

		Text customNr = new Text("KundenNr.: " + customerViewModel.getCustomerId().get());
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
		TableView<ArticleViewModel> articleTable = new TableView<ArticleViewModel>();
		BorderPane centerButtom = new BorderPane();
		
		TableColumn<ArticleViewModel,String> articleNumber = new TableColumn<ArticleViewModel,String>("Artikel Nr.");
		articleNumber.setCellValueFactory(new PropertyValueFactory<ArticleViewModel,String>("articleNumber"));
		articleNumber.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.20));
		
		TableColumn<ArticleViewModel,String> name = new TableColumn<ArticleViewModel,String>("Artikel");
		name.setCellValueFactory(new PropertyValueFactory<ArticleViewModel,String>("name"));
		name.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.40));
		//TODO: cet number of article
        TableColumn numberOfItem = new TableColumn<ArticleViewModel,String>("Anzahl");
        numberOfItem.setCellValueFactory(new PropertyValueFactory<ArticleViewModel,String>("number"));
        numberOfItem.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.10));
        
        TableColumn<ArticleViewModel,String> onePrise = new TableColumn<ArticleViewModel,String>("Einzel Preis");
        onePrise.setCellValueFactory(new PropertyValueFactory<ArticleViewModel,String>("prise"));
        onePrise.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.15));
        //TODO set here number*prise
        TableColumn<ArticleViewModel,String> entirePrise = new TableColumn<ArticleViewModel,String>("Gesamt Preis");
        entirePrise.setCellValueFactory(new PropertyValueFactory<ArticleViewModel,String>("prise"));
        entirePrise.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.15));
        
        articleTable.setItems(articles);
        articleTable.getColumns().addAll(articleNumber, name, numberOfItem, onePrise, entirePrise);

		centerButtom.setCenter(articleTable);
		
		final Button addButton = new Button("Add");
        addButton.setOnAction(e -> addNewItemToTable(articleTable));

	//	midlePane.getChildren().addAll(miniAdress, customAndDate, warenList);
		center.setTop(centerTop);
		center.setCenter(centerButtom);
		center.setBottom(addButton);
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
	
	private void addNewItemToTable(TableView<ArticleViewModel> articleTable){
		articles.add(
        		new ArticleViewModel(
        				new SimpleStringProperty("12345"), 
        				new SimpleStringProperty("Foo"), 
        				new SimpleStringProperty("Chuu"), 
        				new SimpleStringProperty("1"), 
        				new SimpleStringProperty("1"), 
        				new SimpleStringProperty("1.22")
        				)
        		);
		System.out.println("Add new Items to Table");
		articleTable.setItems(articles);
	}

	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}
}
