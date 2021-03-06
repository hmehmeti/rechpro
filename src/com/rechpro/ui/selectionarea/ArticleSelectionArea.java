package com.rechpro.ui.selectionarea;

import java.util.HashMap;
import java.util.Map;

import com.rechpro.worker.ArticleController;
import com.rechpro.worker.ArticleParameters;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.PathClass;

/**
 * @author hmehmeti
 *
 */
public class ArticleSelectionArea extends ElementSelectionArea {
	
	private static final String NEW_ARTICLE_TEXT = "Neuer Artikel";
	
	private ArticleVBoxGenerator articleGenerator;
	private ArticleController articleController;
	private StackPane stackPane;
	private Stage articleStage;

	
	public ArticleSelectionArea() {
		articleGenerator = new ArticleVBoxGenerator();
		articleController = new ArticleController();
		stackPane = new StackPane();
		articleStage = new Stage();
	}

	public Node getTableViewArticles() {
		VBox articleArea = new VBox(VBOX_SPACING);
		// show the list of articles in table form
		loadSelectionArea("ArticleSelectionTable.fxml", stackPane);
		
		// Button and Text to create new article
		Button createNewArticleBtn = getNewArticleCreateButtonWithText();
		VBox createNewArticleBtnAndText = new VBox(VBOX_SPACING);
		createNewArticleBtnAndText.getChildren().addAll(createNewArticleBtn, new Text(NEW_ARTICLE_TEXT));
		articleArea.setSpacing(ELEMENT_AREA_VBOX_SPACING);
		articleArea.setPadding(new Insets(10, 0, 0, 10));
		articleArea.getChildren().addAll(stackPane, createNewArticleBtnAndText);
		return articleArea;
	}
	
	private Button getNewArticleCreateButtonWithText() {
		Button createNewArticleBtn = new Button();
		createNewArticleBtn.setGraphic(articleGenerator.createImageView(this.getClass(), PathClass.ADD_BTN,
				BUTTON_WIDTH_AND_HEIGHT, BUTTON_WIDTH_AND_HEIGHT));
		createNewArticleBtn.setStyle(VBoxGenerator.BUTTON_STYLE);

		// when entering a new article, a new window is shown
		VBox articleCreateWindow = articleGenerator.createAddingNewArticleTable();
		articleStage.setScene(new Scene(articleCreateWindow));
		createNewArticleBtn.setOnAction(event -> {
			articleGenerator.resetInputValues(articleCreateWindow);
			articleGenerator.setTextStyleToValid();
			articleStage.show();
		});
		Button articleSaveBtn = articleGenerator.getSaveButton();
		Button articleCancelBtn = articleGenerator.getCancelButton();
		if (articleSaveBtn != null && articleCancelBtn != null) {
			articleSaveBtn.setOnAction(event -> {
				checkMandatoryFieldsAndSave(articleCreateWindow);
			});
			articleCancelBtn.setOnAction(event -> articleStage.close());
		}

		return createNewArticleBtn;
	}
	
	@Override
	protected void checkMandatoryFieldsAndSave(VBox articleCreateWindow) {
		if (!articleGenerator.areMandatoryInputsDone()) 
			articleGenerator.setInfoMsg(articleCreateWindow, VBoxGenerator.MISSED_REQUIRED_FIELD);
		else {
			int articleNumber = Integer.parseInt(articleGenerator.getArticleNumber().getText());
			
			if(articleController.existsArticleNumber(articleNumber))
				articleGenerator.setInfoMsg(articleCreateWindow, ArticleVBoxGenerator.ARTICLE_NUMBER_EXISTS);
			else {
				transformAndPersist();
				articleStage.close();
				loadSelectionArea("ArticleSelectionTable.fxml", stackPane);
			}
		}
	}
	
	@Override
	public void transformAndPersist() {
		Map<Enum, String> articleParameterList = new HashMap<>();
		articleParameterList.put(ArticleParameters.ARTICLENUMBER, articleGenerator.getArticleNumber().getText());
		articleParameterList.put(ArticleParameters.NAME, articleGenerator.getArticleName().getText());
		articleParameterList.put(ArticleParameters.PRICE, articleGenerator.getArticlePrice().getText());
		articleParameterList.put(ArticleParameters.DESCRIPTION, articleGenerator.getArticleDescription().getText());
		articleParameterList.put(ArticleParameters.CATEGORY, articleGenerator.getArticleCategoryName());
		
		articleController.transformAndPersist(articleParameterList);
	}

}
