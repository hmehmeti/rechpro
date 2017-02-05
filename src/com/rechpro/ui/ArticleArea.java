package com.rechpro.ui;

import java.io.IOException;

import com.rechpro.worker.ArticleController;
import com.rechpro.worker.ArticleVBoxGenerator;
import com.rechpro.worker.CustomerVBoxGenerator;

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
	
	private static final String NEW_ARTICLE_TEXT = "Neuer Artikel";
	private static final int VBOX_SPACING = 2;
	private ArticleController articleController;
	private ArticleVBoxGenerator newArticleGenerator;
	private StackPane stackPane;
	private Stage newArticleStage;

	
	public ArticleArea() {
		articleController = new ArticleController();
		stackPane = new StackPane();
	}

	public Node getTableViewArticles() {
		VBox articleArea = new VBox(VBOX_SPACING);
		// Button and Text to create new article
		VBox createNewArticleBtnAndText = new VBox(VBOX_SPACING);
		Button createNewCustomerBtn = getNewArticleCreatButtonWithText();
		createNewArticleBtnAndText.getChildren().addAll(createNewCustomerBtn, new Text(NEW_ARTICLE_TEXT));
				
		loadArticleSelectionArea();
		
		articleArea.setSpacing(5);
		articleArea.setPadding(new Insets(10, 0, 0, 10));
		articleArea.getChildren().addAll(stackPane, createNewArticleBtnAndText);
		return articleArea;
	}
	
	private Button getNewArticleCreatButtonWithText() {
		Button createNewArticleBtn = new Button();
		createNewArticleBtn.setGraphic(ArticleVBoxGenerator.createImageView(this.getClass(), PathClass.ADD_BTN, 40, 40));
		createNewArticleBtn.setStyle(CustomerVBoxGenerator.BUTTON_STYLE);
		
		// new window if createNewArticle button is clicked
		newArticleStage = new Stage();
		newArticleGenerator = new ArticleVBoxGenerator();
		VBox articleWindow = newArticleGenerator.createAddingNewArticleTable();
		newArticleStage.setScene(new Scene(articleWindow));
		createNewArticleBtn.setOnAction(event-> {
//			resetInputValues(articleWindow);
			newArticleStage.show();
		});
		
		return createNewArticleBtn;
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
