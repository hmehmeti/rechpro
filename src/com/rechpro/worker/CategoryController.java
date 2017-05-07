package com.rechpro.worker;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.rechpro.appcontext.ApplicationContextProvider;
import com.rechpro.entity.Category;
import com.rechpro.persistence.ICategoryDBService;
import com.rechpro.transformer.CategoryTransformer;
import com.rechpro.viewmodel.CategoryViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * View-Controller for the category table
 * @author eosmanlliu
 *
 */
public class CategoryController {
	
	@FXML
	private TextField filterField;
	@FXML
	private TableView<CategoryViewModel> categoryTable;
	@FXML
	private TableColumn<CategoryViewModel, String> categoryNameColumn;
	@FXML
	private TableColumn<CategoryViewModel, String> descriptionColumn;

	private ICategoryDBService dbService;
	private CategoryTransformer transformer;
	
	private ObservableList<CategoryViewModel> masterData = FXCollections.observableArrayList();
	
	public CategoryController() {
		dbService = getCategoryDBService();
		transformer = new CategoryTransformer();
		masterData.addAll(transformer.convertAndGetAllCategories(dbService.retrieveAllCategories()));
	}
	
	private ICategoryDBService getCategoryDBService() {
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		ICategoryDBService dbService = (ICategoryDBService) context.getBean("categoryDBService");
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
		categoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
		
		FilteredList<CategoryViewModel> filteredData = new FilteredList<>(masterData, p -> true);
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(category -> {
				if (newValue == null || newValue.isEmpty())
					return true;
				
				String lowerCaseFilter = newValue.toLowerCase();
				if (category.getCategoryName().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (category.getDescription().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				
				return false;
			});
		});
		
		SortedList<CategoryViewModel> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(categoryTable.comparatorProperty());
		categoryTable.setItems(sortedData);
	}
	
	public boolean existsCategoryName(String categoryName){
		Category category = dbService.retrieveCategory(categoryName);
		if (category != null) {
			return true;
		} 
		return false;
	}

	public void transformAndPersist(Map<Enum, String> categoryParameterList) {
		Category category = transformer.entityFromParameterList(categoryParameterList);
		dbService.createCategory(category);
	}
}
