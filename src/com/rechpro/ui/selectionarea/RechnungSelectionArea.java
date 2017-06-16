package com.rechpro.ui.selectionarea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import com.rechpro.ui.TableGenerator;
import com.rechpro.viewmodel.ArticleViewModelInRechnung;
import com.rechpro.viewmodel.CustomerViewModel;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
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
import javafx.util.Callback;
import resources.PathClass;

/**
 * @author kdogan
 * @email kamuran1905@yahoo.de
 **/
public class RechnungSelectionArea {
	
	private static final String SELLER_ADDRESS = "K�rnerstr. 24 78777 Karlsruhe";
	private static final String INPUT_SUBMIT_BUTTON = "�ndern";
	private static final String CURRENCY = " �";
	public static final ObservableList<ArticleViewModelInRechnung> articles = FXCollections.observableArrayList();

	private CustomerViewModel customerToCreateBill;
	
	private Button inputSubmitBtn = new Button(INPUT_SUBMIT_BUTTON);
	private static Text bruttoBetrag = new Text();
	private static Text mehrwertsteuer = new Text();
	private static Text nettoBetrag = new Text();
	private TextField choosedAmount = new TextField();
	
	private static Stage articleSelectStage;
	private Stage numberInputWindow;
	private TableView<ArticleViewModelInRechnung> articleTable;
	private TableGenerator tableGenerator;
	private ArticleViewModelInRechnung selectedArticle;
	

	public RechnungSelectionArea(CustomerViewModel customerToCreateBill) {
		if(customerToCreateBill !=null)
			RechnungSelectionArea.customerToCreateBill = customerToCreateBill;
		tableGenerator = new TableGenerator();
	}

	private WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
		URL foo = getClass().getResource(name);
		File file = new File(foo.getPath());
		FileInputStream fileInputStream = new FileInputStream(file);
		WordprocessingMLPackage template = WordprocessingMLPackage.load(fileInputStream);
		return template;
	}
	public GridPane addGridPane() {

		GridPane grid = new GridPane();
		BorderPane mainWinBorderPane = new BorderPane();
		mainWinBorderPane.setPrefSize(700, 1000);

		VBox mainWindow = new VBox(20);
		
		//BorderPane header = createHeaderArea();
		BorderPane center = createCenterArea();
		BorderPane footer = createFooterArea();
		
		//mainWinBorderPane.setTop(header);
		mainWinBorderPane.setCenter(center);
		mainWinBorderPane.setBottom(footer);
		
		// right click on Mouse on the article to remove or change the number of article
		articleTable.setRowFactory(
					new Callback<TableView<ArticleViewModelInRechnung>, TableRow<ArticleViewModelInRechnung>>(){
						@Override
						public TableRow<ArticleViewModelInRechnung> call(TableView<ArticleViewModelInRechnung> tableView) {
							final TableRow<ArticleViewModelInRechnung> row = new TableRow<>();
							final ContextMenu rowMenu = getContextMenuForSelectedArticle(row);
							
							row.contextMenuProperty().bind(
									Bindings.when(Bindings.isNotNull(row.itemProperty()))
								      .then(rowMenu)
								      .otherwise((ContextMenu)null));
							
							return row;
						}
					}
				);

		mainWindow.getChildren().addAll(mainWinBorderPane);
		grid.getChildren().add(mainWindow);

		return grid;
	}

	private BorderPane createHeaderArea() {
		BorderPane header = new BorderPane();
		header.setPrefSize(700, 150);
		ImageView logo = createImageView(PathClass.ARTICLE_ICON_PATH, 80, 80);
		header.setRight(logo);
		return header;
	}

	private BorderPane createCenterArea() {
		BorderPane center = new BorderPane();
		center.setPrefSize(700, 400);
		
		BorderPane centerTop = createCenterTopArea();
		BorderPane centerButtom = createCenterButtomArea();
		BorderPane underCenter = createUnderCenterArea();

		center.setTop(centerTop);
		center.setCenter(centerButtom);
		center.setBottom(underCenter);
		return center;
	}
	
	private BorderPane createFooterArea() {
		BorderPane footer = new BorderPane();
		footer.setPrefWidth(700);
		Button createWordFile = new Button();
		createWordFile.setGraphic(createImageView(PathClass.WORD_ICON_PATH, 50, 50));
		
		createWordFile.setOnAction(e -> {
			WordprocessingMLPackage test;
			try {
				test =  getTemplate(PathClass.WORD_TMPL_PATH);
				System.out.println("foo");
			} catch (FileNotFoundException | Docx4JException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} );
		Line line2 = new Line(90, 40, 800, 40);
		line2.setStroke(Color.BLACK);
		Line line3 = new Line(90, 40, 800, 40);
		line3.setStroke(Color.BLACK);
		footer.setTop(line2);
		footer.setLeft(createWordFile);
		footer.setBottom(line3);
		
		return footer;
	}

	private BorderPane createCenterTopArea() {
		BorderPane centerTop = new BorderPane();
		centerTop.setPrefHeight(100);

		VBox sellerAddressMini = getSellerMiniAddress();
		VBox leftColumn = getLeftColumn();
		VBox rightColumn = getRightColumn();
		VBox rechnungTextRow = getRechnungTextRow();

		centerTop.setTop(sellerAddressMini);
		centerTop.setLeft(leftColumn);
		centerTop.setRight(rightColumn);
		centerTop.setBottom(rechnungTextRow);
		
		return centerTop;
	}

	private BorderPane createUnderCenterArea() {
		final Button addButton = new Button("Artikel Hinzuf�gen");
		addButton.setOnAction(e -> openArticlesWindow());
		BorderPane underCenter = new BorderPane();
		Text doppelPunkt = new Text(" : ");
		Text nettoBetragText = new Text("Nettobetrag ");
		HBox nettoBetragColumn = new HBox();
		nettoBetragColumn.getChildren().addAll(nettoBetragText, doppelPunkt, nettoBetrag, new Text(CURRENCY));
		
		Text steuerText = new Text("zzgl. 19 % MwSt. ");
		Text doppelPunkt2 = new Text(" : ");
		HBox steuerColumn = new HBox();
		steuerColumn.getChildren().addAll(steuerText, doppelPunkt2, mehrwertsteuer, new Text(CURRENCY));
		
		Text bruttoBetragText = new Text("Bruttobetrag ");
		bruttoBetragText.setFont(new Font("Arial", 15));
		Text doppelPunkt3 = new Text(" : ");
		updateBillAmound();
		HBox bruttoBetragColumn = new HBox();
		bruttoBetragColumn.getChildren().addAll(bruttoBetragText, doppelPunkt3, bruttoBetrag, new Text(CURRENCY));
		VBox PriceTotalColumn = new VBox();
		PriceTotalColumn.getChildren().addAll(nettoBetragColumn, steuerColumn, bruttoBetragColumn);
		underCenter.setRight(PriceTotalColumn);
		underCenter.setLeft(addButton);
		
		return underCenter;
	}

	private BorderPane createCenterButtomArea() {
		articleTable = tableGenerator.getSelectedArticleTable();
		BorderPane centerButtom = new BorderPane();
		articleTable.setItems(articles);
		centerButtom.setCenter(articleTable);
		return centerButtom;
	}

	private ContextMenu getContextMenuForSelectedArticle(TableRow<ArticleViewModelInRechnung> selectedRow) {
		ContextMenu contextMenu = new ContextMenu();

		MenuItem deleteArticle = new MenuItem("Artikel L�schen");
		MenuItem updateArticleNumber = new MenuItem("Anzahl �ndern");

		updateArticleNumber.setOnAction(e -> {
			selectedArticle = selectedRow.getItem();
			// show actual amount
			choosedAmount.setText(String.valueOf(selectedArticle.getAmount()));
			numberInputWindow = getNumberInputWindow();
			numberInputWindow.show();
		});

		
		inputSubmitBtn.setOnAction(e -> {
			// set input value as new amount
			int amount = Integer.parseInt(choosedAmount.getCharacters().toString());
			checkChoosedAmountAndSetArticleNumber(amount);
			numberInputWindow.close();
		});

		deleteArticle.setOnAction(e -> {
			ArticleViewModelInRechnung selectedArticle = selectedRow.getItem();
			deleteSelectedArticle(selectedArticle);
		});
		contextMenu.getItems().addAll(deleteArticle, updateArticleNumber);

		return contextMenu;
	}

	private void deleteSelectedArticle(ArticleViewModelInRechnung selectedArticle) {
		if (articles.contains(selectedArticle))
			articles.remove(selectedArticle);
		else
			return;
		
		updateBillAmound();
	}

	private void checkChoosedAmountAndSetArticleNumber(int amount) {
		try {
			if (amount != 0) {
				if (amount != 0)
					selectedArticle.setAmount(amount);
				updateArticleInTableView(selectedArticle);
			} else
				return;
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
	private void updateArticleInTableView(ArticleViewModelInRechnung selectedArticle) {
		for(int i = 0; i < articles.size(); i++){
			ArticleViewModelInRechnung article = articles.get(i);
			if (article.getArticleNumber() == selectedArticle.getArticleNumber())
				articles.set(i, selectedArticle);
		}
		updateBillAmound();
	}

	private Stage getNumberInputWindow() {
		Stage newStage = new Stage();
		newStage.setTitle("Neue Anzahl Eingeben");
		newStage.initStyle(StageStyle.UTILITY);
		newStage.setResizable(false);
		inputSubmitBtn.setPrefWidth(70);
		HBox comp = new HBox();
		choosedAmount.setPrefWidth(70);
		comp.getChildren().addAll(choosedAmount, inputSubmitBtn);
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
		String pattern = "dd.MM.YYYY";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Text dateText = new Text("Datum: ".concat(dateFormat.format(new Date())));
		if (customerToCreateBill != null)
			customerNr = new Text("KundenNr.: " + customerToCreateBill.getCustomerId().get());
		else
			customerNr = new Text("KundenNr.: ");
		Text rechnungNr = new Text("RechungsNr.: ");

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
		Text sellerAddress = new Text(SELLER_ADDRESS);
		sellerAddress.setFont(Font.font("Verdana", 8));
		int addressLength = (sellerAddress.getText().length())*8;
		Line line = new Line(90, 40, addressLength, 40);
		line.setStroke(Color.BLACK);
		miniAdress.getChildren().addAll(sellerAddress, line);

		return miniAdress;
	}

	public static void closeArticleSelectStage() {
		if (articleSelectStage == null)
			return;
		articleSelectStage.close();
	}

	public static void updateBillAmound() {
		String pattern = "###,###.###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		double nettoBetragX = calculateNettoBetrag();
		double vat = calculateMehrwertsteuer(nettoBetragX);
		double bruttoBetragX = nettoBetragX + vat;
		mehrwertsteuer.setText(decimalFormat.format(vat));
		bruttoBetrag.setText(decimalFormat.format(bruttoBetragX));
		nettoBetrag.setText(decimalFormat.format(nettoBetragX));
	}
	
	private static double calculateNettoBetrag() {
		double betrag = 0;
		for(ArticleViewModelInRechnung article: articles)
			betrag = betrag + article.getTotalPrice();
		return betrag;
	}

	private static double calculateMehrwertsteuer(double betrag) {
		
		return (betrag* 19)/100;
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
