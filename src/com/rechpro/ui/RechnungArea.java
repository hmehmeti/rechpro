package com.rechpro.ui;

import java.io.IOException;

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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resources.PathClass;

/**
 * @author kdogan
 * @email kamuran1905@yahoo.de
 **/
public class RechnungArea {
	public static CustomerViewModel customerToCreateBill;
	private static Stage articleSelectStage;
	private TableGenerator tableGenerator;
	private TableView<ArticleViewModelInRechnung> articleTable;
	private Button inputSubmitBtn = new Button("Ändern");
	private int articleNumber = 0;
	private TextField choisedNumber;
	private ArticleViewModelInRechnung selectedArticle;
	private Stage numberInputWindow;

	public final static ObservableList<ArticleViewModelInRechnung> articles = FXCollections.observableArrayList();

	public RechnungArea() {
		tableGenerator = new TableGenerator();
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

		// ------------Address of seller ----------------------//
		BorderPane center = new BorderPane();
		center.setPrefSize(700, 400);
		BorderPane centerTop = new BorderPane();
		centerTop.setPrefHeight(100);

		/** (Right) Seller and Customer Address | (Left) Date, Customer and Rechnung Nr. **/
		VBox sellerAddressMini = getSellerMiniAddress();
		VBox leftColumn = getLeftColumn();
		VBox rightColumn = getRightColumn();
		VBox rechnungTextRow = getRechnungTextRow();

		centerTop.setTop(sellerAddressMini);
		centerTop.setLeft(leftColumn);
		centerTop.setRight(rightColumn);
		centerTop.setBottom(rechnungTextRow);

		/** (Right) Seller and Customer Address | (Left) Date, Customer and Rechnung Nr. end **/

		/** list of selected articles **/
		articleTable = tableGenerator.getSelectedArticleTable();
		BorderPane centerButtom = new BorderPane();
		articleTable.setItems(articles);
		centerButtom.setCenter(articleTable);

		final Button addButton = new Button("Artikel Hinzufügen");
		addButton.setOnAction(e -> openArticlesWindow());
		BorderPane underCenter = new BorderPane();
		Text doppelPunkt = new Text(" : ");
		Text nettoBetragText = new Text("Nettobetrag ");
		Text nettoBetrag = new Text("<Betrag>");
		HBox nettoBetragColumn = new HBox();
		nettoBetragColumn.getChildren().addAll(nettoBetragText, doppelPunkt, nettoBetrag);
		
		Text steuerText = new Text("zzgl. 19 % MwSt. ");
		Text doppelPunkt2 = new Text(" : ");
		Text steuerBetrag = new Text("<Steuer Betrag>");
		HBox steuerColumn = new HBox();
		steuerColumn.getChildren().addAll(steuerText, doppelPunkt2, steuerBetrag);
		
		Text bruttoBetragText = new Text("Bruttobetrag ");
		bruttoBetragText.setFont(new Font("Arial", 15));
		Text doppelPunkt3 = new Text(" : ");
		Text bruttoBetrag = new Text(""+calculateBetrag());
		HBox bruttoBetragColumn = new HBox();
		bruttoBetragColumn.getChildren().addAll(bruttoBetragText, doppelPunkt3, bruttoBetrag);
		VBox test = new VBox();
		test.getChildren().addAll(nettoBetragColumn, steuerColumn, bruttoBetragColumn);
		underCenter.setRight(test);
		underCenter.setLeft(addButton);
		
		center.setTop(centerTop);
		center.setCenter(centerButtom);
		center.setBottom(underCenter);
		mainWinBorderPane.setCenter(center);

		// right click on Mouse on the article to remove or change the number of article
		articleTable.setOnContextMenuRequested(e -> {
			getContextMenu().show(center, e.getScreenX(), e.getScreenY());
			selectedArticle = (ArticleViewModelInRechnung) articleTable.getSelectionModel().getSelectedItem();
		});

		/** list of selected articles end **/

		/************ footer ***************/
		VBox footer = new VBox(5);
		footer.setPrefWidth(700);

		Text footerInfo = new Text("Füß Informationen");
		Line line2 = new Line(90, 40, 800, 40);
		line2.setStroke(Color.BLACK);
		Line line3 = new Line(90, 40, 800, 40);
		line3.setStroke(Color.BLACK);

		footer.getChildren().addAll(line2, footerInfo, line3);

		mainWinBorderPane.setBottom(footer);
		/********* footer end **************/

		mainWindow.getChildren().addAll(mainWinBorderPane);
		grid.getChildren().add(mainWindow);

		return grid;
	}

	private double calculateBetrag() {
		double betrag = 0;
		for(ArticleViewModelInRechnung article: articles)
			betrag = betrag + article.getTotalPrise();

		return betrag;
	}

	private ContextMenu getContextMenu() {
		ContextMenu contextMenu = new ContextMenu();

		MenuItem deleteArticle = new MenuItem("Artikel Löschen");
		MenuItem updateArticleNumber = new MenuItem("Anzahl Ändern");

		updateArticleNumber.setOnAction(e -> {
			numberInputWindow = getNumberInputWindow();
			numberInputWindow.show();
		});

		inputSubmitBtn.setOnAction(e -> {
			checkChoisedNumberAndSetArticleNumber();
			numberInputWindow.close();
		});

		deleteArticle.setOnAction(e -> deleteSelectedArticle());
		contextMenu.getItems().addAll(deleteArticle, updateArticleNumber);

		return contextMenu;
	}

	private void deleteSelectedArticle() {
		if (articles.contains(selectedArticle))
			articles.remove(selectedArticle);
		else
			return;
	}

	private void checkChoisedNumberAndSetArticleNumber() {
		int choisedNumberValue = 0;
		try {
			choisedNumberValue = Integer.parseInt(choisedNumber.getText());
			if (choisedNumberValue != 0) {
				articleNumber = choisedNumberValue;
				editSelectedArticleNumber();
			} else
				return;
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}

	private Stage getNumberInputWindow() {
		Stage newStage = new Stage();
		newStage.setTitle("Waren Anzahl Eingeben");
		newStage.initStyle(StageStyle.UTILITY);
		newStage.setResizable(false);
		inputSubmitBtn.setPrefWidth(30);
		HBox comp = new HBox();
		choisedNumber = new TextField();
		choisedNumber.setPrefWidth(70);
		comp.getChildren().addAll(choisedNumber, inputSubmitBtn);
		Scene stageScene = new Scene(comp, 200, 50);
		newStage.setScene(stageScene);
		return newStage;
	}

	private VBox getLeftColumn() {
		VBox customerAndAddress = new VBox(5);
		if (customerToCreateBill != null) {
			String customName = customerToCreateBill.getFirstName().get() + " "	+ customerToCreateBill.getLastName().get();
			Text custumerName = new Text(customName);
			String street = customerToCreateBill.getStreet().get();
			String number = customerToCreateBill.getNo().get();
			String postCode = customerToCreateBill.getPostCode().get();
			String city = customerToCreateBill.getCity().get();

			Text custumerAdress = new Text(street + " " + number + "\n" + postCode + " " + city);
			customerAndAddress.getChildren().addAll(custumerName, custumerAdress);
		} else {
			Text custumerAdress = new Text("Musterstr. 123 \n" + "MusterPLZ Musterstadt ");
			customerAndAddress.getChildren().addAll(new Text("Muster Kunde"), custumerAdress);
		}
		return customerAndAddress;
	}

	private VBox getRightColumn() {
		VBox rightColumn = new VBox(5);
		Text customerNr;
		Text dateText = new Text("Datum: 12.12.2014");
		if (customerToCreateBill != null)
			customerNr = new Text("KundenNr.: " + customerToCreateBill.getCustomerId().get());
		else
			customerNr = new Text("KundenNr.: 123456789");
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
		billHBox.getChildren().addAll(billText, line4);
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

	public static void closeArticleSelectStage() {
		if (articleSelectStage == null)
			return;
		articleSelectStage.close();
	}

	private void editSelectedArticleNumber() {
		if (articleNumber != 0)
			selectedArticle.setNumber(articleNumber);
		
		updateArticleInTableView(selectedArticle);
	}

	private void updateArticleInTableView(ArticleViewModelInRechnung selectedArticle) {

		for(int i = 0; i < articles.size(); i++){
			ArticleViewModelInRechnung article = articles.get(i);
			if (article.getArticleNumber() == selectedArticle.getArticleNumber())
				articles.set(i, selectedArticle);
		}
	}

	private void openArticlesWindow() {
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
}
