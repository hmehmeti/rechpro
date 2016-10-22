package com.rechpro.worker;

import com.rechpro.viewmodel.CustomerViewModel;

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
public class PersonTableController {
	
	@FXML
	private TextField filterField;
	@FXML
	private TableView<CustomerViewModel> personTable;
	@FXML
	private TableColumn<CustomerViewModel, String> customerId;
	@FXML
	private TableColumn<CustomerViewModel, String> firstNameColumn;
	
	@FXML
	private TableColumn<CustomerViewModel, String> lastNameColumn;

	private ObservableList<CustomerViewModel> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public PersonTableController() {
		fetchCustomerViewModelsFromDatabase();
	}

	private void fetchCustomerViewModelsFromDatabase() {
		TestCustomers customerGen = new TestCustomers();
		//TODO hier musst ein Thread gestartet werden. In dieser thread musst ein while (true) gestartet
		masterData.add(customerGen.getUser1());
		masterData.add(customerGen.getUser2());
		masterData.add(customerGen.getUser3());
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
		customerId.setCellValueFactory(cellData -> cellData.getValue().getCustomerId());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<CustomerViewModel> filteredData = new FilteredList<>(masterData, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (person.getFirstName().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (person.getCustomerId().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (person.getLastName().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<CustomerViewModel> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(personTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		personTable.setItems(sortedData);
	}
}