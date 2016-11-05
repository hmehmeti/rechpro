package com.rechpro.worker;

import com.rechpro.entity.Article;
import com.rechpro.persistence.DBService;
import com.rechpro.transformer.ArticleTransformer;
import com.rechpro.viewmodel.ArticleViewModel;

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
public class ArticleSelector {
	
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
	
	private ArticleTransformer transformer;
	private DBService<Article> dbService;
	

	private ObservableList<ArticleViewModel> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public ArticleSelector() {
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
		// 0. Initialize the columns.
		articleId.setCellValueFactory(cellData -> cellData.getValue().getArticleNumber());
		articleNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategory());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<ArticleViewModel> filteredData = new FilteredList<>(masterData, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(article -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty())
					return true;
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (article.getArticleNumber().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (article.getName().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (article.getCategory().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<ArticleViewModel> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(articleTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		articleTable.setItems(sortedData);
	}
}