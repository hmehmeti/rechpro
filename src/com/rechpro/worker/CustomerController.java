package com.rechpro.worker;

import java.io.IOException;
import java.util.HashMap;

import com.rechpro.entity.Customer;
import com.rechpro.persistence.CustomerDBService;
import com.rechpro.transformer.CustomerTransformer;
import com.rechpro.viewmodel.CustomerViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CustomerController {

    @FXML
    private TextField filterField;
    @FXML
    private TableView<CustomerViewModel> customerTable;
    @FXML
    private TableColumn<CustomerViewModel, String> customerId;
    @FXML
    private TableColumn<CustomerViewModel, String> customerName;
    @FXML
    private TableColumn<CustomerViewModel, String> customerAddress;
    
    private ObservableList<CustomerViewModel> masterData = FXCollections.observableArrayList();
	
	private CustomerTransformer transformer;
	private CustomerDBService dbService;

	public CustomerController() {
//		customerTable = new TableView<CustomerViewModel>();
//		customerId = new TableColumn<>();
//		customerName = new TableColumn<>();
//		customerAddress = new TableColumn<>();
		// Add some sample data to the master data
		TestCustomers customersGenerator = new TestCustomers();
        masterData.add(customersGenerator.getUser1());
        masterData.add(customersGenerator.getUser2());
        
        transformer = new CustomerTransformer();
        dbService = new CustomerDBService();
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
		customerName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		customerAddress.setCellValueFactory(cellData -> cellData.getValue().getAddress());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<CustomerViewModel> filteredData = new FilteredList<>(masterData, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(customer -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (customer.getCustomerId().get().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (customer.getFirstName().get().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (customer.getAddress().get().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
    
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<CustomerViewModel> sortedData = new SortedList<>(filteredData);
				
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(customerTable.comparatorProperty());
				
		// 5. Add sorted (and filtered) data to the table.
		customerTable.setItems(sortedData);
	}

	public void transformAndPersist(HashMap<Enum, String> customParameterList) {
		Customer customer = transformer.entityFromParameterList(customParameterList);
		dbService.addCustomer(customer);
	}

}
