package com.rechpro.worker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import com.rechpro.appcontext.ApplicationContextProvider;
import com.rechpro.entity.Article;
import com.rechpro.persistence.IArticleDBService;
import com.rechpro.transformer.ArticleTransformer;
import com.rechpro.ui.selectionarea.RechnungSelectionArea;
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
 * View-Controller for the article table.
 * 
 * @author Kamuran Dogan
 */
public class ArticleController {

	@FXML
	private TextField filterField;
	@FXML
	private TableView<ArticleViewModel> articleTable;
	@FXML
	private TableColumn<ArticleViewModel, String> articleNumberColumn;
	@FXML
	private TableColumn<ArticleViewModel, String> articleNameColumn;
	@FXML
	private TableColumn<ArticleViewModel, String> categoryColumn;
	@FXML
	private TableColumn<ArticleViewModel, String> descriptionColumn;

	private ArticleViewModel selectedArticle;
	private ArticleTransformer transformer;
	private IArticleDBService dbService;

	private ObservableList<ArticleViewModel> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public ArticleController() {
		dbService = getArticleDBService();
		transformer = new ArticleTransformer();
		masterData.addAll(transformer.convertAndGetAllArticle(dbService.retrieveAllArticles()));
	}

	private IArticleDBService getArticleDBService() {
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		IArticleDBService dbService = (IArticleDBService) context.getBean("articleDBService");
		return dbService;
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * Initializes the table columns and sets up sorting and filtering.
	 */
	@FXML
	private void initialize() {
		articleNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getArticleNumber());
		articleNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());

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
				else if (article.getCategoryName().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true; 
				else if (article.getDescription().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				
				return false;
			});
		});
		
		articleTable.setOnMousePressed(e -> {
			boolean isMouseDoppelClicked = e.isPrimaryButtonDown() && e.getClickCount() == 2;
			if (isMouseDoppelClicked) {
				selectedArticle = articleTable.getSelectionModel().getSelectedItem();
				ArticleViewModelInRechnung a = transformer.articleViewModel2ArticleViewModelInRechnung(selectedArticle);
				if(!RechnungSelectionArea.articles.contains(a))
					RechnungSelectionArea.articles.add(a);
				RechnungSelectionArea.updateBillAmound();
				RechnungSelectionArea.closeArticleSelectStage();
			}
		});

		SortedList<ArticleViewModel> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(articleTable.comparatorProperty());
		articleTable.setItems(sortedData);
	}

	public void transformAndPersist(Map<Enum, String> articleParameterList) {
		Article article = transformer.entityFromParameterList(articleParameterList);
		dbService.createArticle(article);
	}

	public boolean existsArticleNumber(int articleNumber) {
		List<Article> articleEntities = dbService.retrieveAllArticles();
		List<Article> filteredResult = articleEntities.stream().filter(o->o.getArticleNumber() == articleNumber).collect(Collectors.toList());
		if(!filteredResult.isEmpty())
			return true;
		
		return false;
	}
}