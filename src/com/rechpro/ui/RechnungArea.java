package com.rechpro.ui;

import java.io.IOException;
import java.util.ArrayList;

import com.rechpro.entity.Customer;
import com.rechpro.persistence.DBService;
import com.rechpro.transformer.CustomerTransformer;
import com.rechpro.viewmodel.ArticleViewModelInRechnung;
import com.rechpro.viewmodel.CustomerViewModel;
import com.rechpro.worker.TableGenerator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.PathClass;

/**
 * @author kdogan
 * @email kamuran1905@yahoo.de
 **/
public class RechnungArea {
	private CustomerTransformer transformer;
	private DBService<Customer> dbService;
	private CustomerViewModel customerViewModel;
	private static Stage articleSelectStage;
	private TableGenerator tableGenerator;
	private TableView<ArticleViewModelInRechnung> articleTable;
	
	public final static ObservableList<ArticleViewModelInRechnung> articles = FXCollections.observableArrayList();
	public RechnungArea() {
		tableGenerator = new TableGenerator();
		transformer = new CustomerTransformer();
        dbService = new DBService<Customer>("Customer");
        ArrayList<Customer> customers = (ArrayList<Customer>)dbService.getEntities();
        //TODO hier muss ausgewählte Customer
        customerViewModel = transformer.entityToViewModel(customers.get(0));
	}
	
	public GridPane addGridPane() {

		GridPane grid = new GridPane();
		BorderPane mainWinBorderPane = new BorderPane();
		mainWinBorderPane.setPrefSize(700, 1000);

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

		//////// (Right) Seller and Customer Address | (Left) Date, Customer and Rechnung Nr.  ////////
		VBox sellerAddressMini = getSellerMiniAddress();
		VBox leftColumn = getLeftColumn();
		VBox rightColumn = getRightColumn();
		VBox rechnungTextRow = getRechnungTextRow();
		
		centerTop.setTop(sellerAddressMini);
		centerTop.setLeft(leftColumn);
		centerTop.setRight(rightColumn);
		centerTop.setBottom(rechnungTextRow);
		
		////////(Right) Seller and Customer Address | (Left) Date, Customer and Rechnung Nr.  end ////////

		///////////////// list of selected articles //////////////////////////////
		articleTable = tableGenerator.getSelectedArticleTable();
		BorderPane centerButtom = new BorderPane();
        articleTable.setItems(articles);
		centerButtom.setCenter(articleTable);
		
		final Button addButton = new Button("Ware Hinzufügen");
        addButton.setOnAction(e -> addCustomerSelectionArea());

		center.setTop(centerTop);
		center.setCenter(centerButtom);
		center.setBottom(addButton);
		mainWinBorderPane.setCenter(center);
		
		articleTable.setOnMousePressed(e -> editSelectedArticleNumber(e));
		
		
		///////////////// list of selected articles end /////////////////////////////

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
	
	private VBox getLeftColumn() {
		VBox customerAndAddress = new VBox(5);
		String customName = customerViewModel.getFirstName().get() +" "+ customerViewModel.getLastName().get();
		Text custumerName = new Text(customName);
		//TODO: hier muss die Adresse nachdem Hausnummer in zweite Zeile geschrieben werden
		String street = customerViewModel.getStreet().get();
		String number = customerViewModel.getNo().get();
		String postCode = customerViewModel.getPostCode().get();
		String city = customerViewModel.getCity().get();

		Text custumerAdress = new Text(street + " " + number + "\n" + postCode + " " + city);
		customerAndAddress.getChildren().addAll(custumerName, custumerAdress);
		
		return customerAndAddress;
	}

	private VBox getRightColumn() {
		VBox rightColumn = new VBox(5);
		
		Text dateText = new Text("Datum: 12.12.2014");
		Text customerNr = new Text("KundenNr.: " + customerViewModel.getCustomerId().get());
		Text rechnungNr = new Text("RechungsNr.: 1234567887");
		
		rightColumn.getChildren().addAll(dateText, customerNr, rechnungNr);
		return rightColumn;
	}

	private VBox getRechnungTextRow() {
		VBox billHBox = new VBox();
		Label billText = new Label("RECHNUNG");
		billText.setFont(new Font("Arial", 30));
		Line line4 = new Line(90, 40, 800, 40);
	    line4.setStroke(Color.BLACK);
		billHBox.getChildren().addAll(billText,line4);
		return billHBox;
	}

	private VBox getSellerMiniAddress() {
		VBox miniAdress = new VBox(3);
		// TODO : get address from from config class
		Text sellerAddress = new Text("Körnerstr. 24 78777 Karlsruhe");
		sellerAddress.setFont(Font.font("Verdana", 8));
		Line line = new Line(90, 40, 350, 40);
	    line.setStroke(Color.BLACK);
		miniAdress.getChildren().addAll(sellerAddress, line);
		
		return miniAdress;
	}

	public static void closeArticleSelectStage(){
		if (articleSelectStage == null)
			return;
		articleSelectStage.close();
	}
	
	private void editSelectedArticleNumber(MouseEvent e) {
		if (e.isPrimaryButtonDown() && e.getClickCount() == 1) {
			ArticleViewModelInRechnung selectedArticle = articleTable.getSelectionModel().getSelectedItem();
			selectedArticle.setNumber(2);
			updateArticleInTableView(selectedArticle);
		}
	}

	private void addCustomerSelectionArea() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ArticleSelectionTable.fxml"));
		try {
			articleSelectStage = new Stage();
			AnchorPane customerSelectionArea = (AnchorPane) loader.load();
			articleSelectStage.setScene(new Scene(customerSelectionArea));
			articleSelectStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}
	
	private void updateArticleInTableView(ArticleViewModelInRechnung selectedArticle) {
		for(ArticleViewModelInRechnung article: articles){
			if(article.getArticleNumber() == selectedArticle.getArticleNumber()){
				articles.remove(article);
				articles.add(selectedArticle);
			}
		}
	}
}
