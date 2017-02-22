package com.rechpro.ui;

import java.io.IOException;
import java.util.HashMap;

import com.rechpro.worker.ArticleController;
import com.rechpro.worker.ArticleParameters;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.PathClass;

/**
 * @author hmehmeti
 *
 */
public class ArticleArea {
	
	private static final int ARTICLE_AREA_VBOX_SPACING = 5;
	private static final int BUTTON_WIDTH_AND_HEIGHT = 20;
	private static final String NEW_ARTICLE_TEXT = "Neuer Artikel";
	private static final int VBOX_SPACING = 2;
	private ArticleVBoxGenerator articleGenerator;
	private ArticleController articleController;
	private StackPane stackPane;
	private Stage articleStage;

	
	public ArticleArea() {
		articleGenerator = new ArticleVBoxGenerator();
		articleController = new ArticleController();
		stackPane = new StackPane();
		articleStage = new Stage();
		loadArticleSelectionArea();
	}

	public Node getTableViewArticles() {
		VBox articleArea = new VBox(VBOX_SPACING);
		// Button and Text to create new article
		VBox createNewArticleBtnAndText = new VBox(VBOX_SPACING);
		Button createNewArticleBtn = getNewArticleCreatButtonWithText();
		createNewArticleBtnAndText.getChildren().addAll(createNewArticleBtn, new Text(NEW_ARTICLE_TEXT));
		
		//when entering a new article, a new window is shown
		VBox articleCreateWindow = articleGenerator.createAddingNewArticleTable();
		articleStage.setScene(new Scene(articleCreateWindow));
		createNewArticleBtn.setOnAction(event-> {
			articleGenerator.resetInputValues(articleCreateWindow);
			articleGenerator.setTextStyleToValid();
			articleStage.show();
		});
		Button articleSaveBtn = articleGenerator.getSaveButton();
		Button articleCancelBtn = articleGenerator.getCancelButton();
		articleSaveBtn.setOnAction(event-> {
			checkMandatoryFieldsAndSaveArticle(articleCreateWindow);
		});
		articleCancelBtn.setOnAction(event->articleStage.close());
				
		articleArea.setSpacing(ARTICLE_AREA_VBOX_SPACING);
		articleArea.setPadding(new Insets(10, 0, 0, 10));
		articleArea.getChildren().addAll(stackPane, createNewArticleBtnAndText);
		return articleArea;
	}
	
	private void checkMandatoryFieldsAndSaveArticle(VBox articleCreateWindow) {
		if (!articleGenerator.areMandatoryInputsDone()) 
			articleGenerator.setInfoMsg(articleCreateWindow, VBoxGenerator.KEINE_OBLIGATORISCHE_FELDER);
		else {
			int articleNumber = Integer.parseInt(articleGenerator.getArticleNumber().getText());
			
			if(articleController.existsArticleNumber(articleNumber))
				articleGenerator.setInfoMsg(articleCreateWindow, ArticleVBoxGenerator.ARTICLE_NUMBER_EXISTS);
			else {
				transformAndPersist();
				articleStage.close();
				loadArticleSelectionArea();
			}
		}
	}

	private Button getNewArticleCreatButtonWithText() {
		Button createNewArticleBtn = new Button();
		createNewArticleBtn.setGraphic(ArticleVBoxGenerator
				.createImageView(this.getClass(), PathClass.ADD_BTN, BUTTON_WIDTH_AND_HEIGHT, BUTTON_WIDTH_AND_HEIGHT));
		createNewArticleBtn.setStyle(CustomerVBoxGenerator.BUTTON_STYLE);
		
		// new window if createNewArticle button is clicked
		VBox articleWindow = articleGenerator.createAddingNewArticleTable();
		articleStage.setScene(new Scene(articleWindow));
		createNewArticleBtn.setOnAction(event-> {
//			resetInputValues(articleWindow);
			articleStage.show();
		});
		
		return createNewArticleBtn;
	}
	
	private void transformAndPersist() {
		HashMap<Enum, String> articleParameterList = new HashMap<Enum, String>();
		
		articleParameterList.put(ArticleParameters.ARTICLENUMBER, articleGenerator.getArticleNumber().getText());
		articleParameterList.put(ArticleParameters.NAME, articleGenerator.getArticleName().getText());
		articleParameterList.put(ArticleParameters.PRICE, articleGenerator.getArticlePrice().getText());
		articleParameterList.put(ArticleParameters.DESCRIPTION, articleGenerator.getArticleDescription().getText());
		articleParameterList.put(ArticleParameters.CATEGORY, articleGenerator.getArticleCategoryName());
		
		articleController.transformAndPersist(articleParameterList);
	}

	/**
	 * Hier wird die Tabelle, in der die Artikeln gelistet und gesucht werden können, erzeugt.
	 */
	private void loadArticleSelectionArea() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ArticleSelectionTable.fxml"));
		try {
			AnchorPane articleSelectionArea = (AnchorPane) loader.load();
			stackPane.getChildren().add(articleSelectionArea);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
