package com.rechpro.worker;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.engine.internal.Collections;

import com.rechpro.entity.Article;
import com.rechpro.persistence.DBService;
import com.rechpro.transformer.ArticleTransformer;
import com.rechpro.ui.RechnungArea;
import com.rechpro.viewmodel.ArticleViewModel;
import com.rechpro.viewmodel.ArticleViewModelInRechnung;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * View-Controller for the person table.
 * 
 * @author Kamuran Dogan
 */
public class ArticleController {

	@FXML
	private TextField filterField;
	@FXML
	private TableView<ArticleViewModel> articleTable;
	@FXML
	private TableColumn<ArticleViewModel, String> articleId;
	@FXML
	private TableColumn<ArticleViewModel, String> articleNameColumn;
	@FXML
	private TableColumn<ArticleViewModel, String> categoryColumn;

	private ArticleViewModel selectedArticle;
	private ArticleTransformer transformer;
	private DBService<Article> dbService;

	private ObservableList<ArticleViewModel> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public ArticleController() {
		transformer = new ArticleTransformer();
		dbService = new DBService<Article>("Article");
		masterData.addAll(transformer.convertAndGetAllArticle(dbService.getEntities()));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * Initializes the table columns and sets up sorting and filtering.
	 */
	@FXML
	private void initialize() {
		articleId.setCellValueFactory(cellData -> cellData.getValue().getArticleNumber());
		articleNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategory());

		FilteredList<ArticleViewModel> filteredData = new FilteredList<>(masterData, p -> true);

		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(article -> {
				
				if (newValue == null || newValue.isEmpty())
					return true;

				String lowerCaseFilter = newValue.toLowerCase();

				if (article.getArticleNumber().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) 
					return true; 
				else if (article.getName().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				else if (article.getCategory().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true; 
				
				return false;
			});
		});
		
		articleTable.setOnMousePressed(e -> {
			boolean isMouseDoppelClicked = e.isPrimaryButtonDown() && e.getClickCount() == 2;
			if (isMouseDoppelClicked) {
				selectedArticle = articleTable.getSelectionModel().getSelectedItem();
				ArticleViewModelInRechnung a = transformer.articleViewModel2ArticleViewModelInRechnung(selectedArticle);
				if(!RechnungArea.articles.contains(a))
					RechnungArea.articles.add(a);
				RechnungArea.updateBillAmound();
				RechnungArea.closeArticleSelectStage();
			}
		});

		SortedList<ArticleViewModel> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(articleTable.comparatorProperty());
		articleTable.setItems(sortedData);
	}

	public void transformAndPersist(HashMap<Enum, String> articleParameterList) {
		Article article = transformer.entityFromParameterList(articleParameterList);
		dbService.addEntity(article);
	}

	public boolean existWarenNummer(int warennummer) {
		List<Article> articleEntities = dbService.getEntities();
		List<Article> foo = articleEntities.stream().filter(o->o.getArticleNumber() == warennummer).collect(Collectors.toList());
		if(!foo.isEmpty())
			return true;
		
		return false;
	}
}