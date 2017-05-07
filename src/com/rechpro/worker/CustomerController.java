package com.rechpro.worker;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.rechpro.appcontext.ApplicationContextProvider;
import com.rechpro.entity.Customer;
import com.rechpro.persistence.CategoryDBService;
import com.rechpro.persistence.CustomerDBService;
import com.rechpro.persistence.ICustomerDBService;
import com.rechpro.transformer.CustomerTransformer;
import com.rechpro.ui.Launcher;
import com.rechpro.ui.area.ButtonOnLeftArea;
import com.rechpro.ui.area.CenterArea;
import com.rechpro.ui.selectionarea.RechnungSelectionArea;
import com.rechpro.viewmodel.CustomerViewModel;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * View-Controller for the person table.
 * 
 * @author Kamuran Dogan
 */
public class CustomerController {

	@FXML
	private TextField filterField;
	@FXML
	private TableView<CustomerViewModel> customerTable;
	@FXML
	private TableColumn<CustomerViewModel, String> customerIdColumn;
	@FXML
	private TableColumn<CustomerViewModel, String> firstNameColumn;
	@FXML
	private TableColumn<CustomerViewModel, String> lastNameColumn;

	private CustomerTransformer transformer;
	private ICustomerDBService dbService;
	
	private ObservableList<CustomerViewModel> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public CustomerController() {
		dbService = getCustomerDBServiceBean();
		transformer = new CustomerTransformer();
		masterData.addAll(transformer.convertAndGetAllCustomer(dbService.retrieveAllCustomers()));
	}
	
	private ICustomerDBService getCustomerDBServiceBean() {
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		ICustomerDBService dbService = (ICustomerDBService) context.getBean("customerDBService");
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
		// 0. Initialize the columns.
		customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().getCustomerId());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());

		// 1. Wrap the ObservableList in a FilteredList (initially display all
		// data).
		FilteredList<CustomerViewModel> filteredData = new FilteredList<>(masterData, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(customer -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty())
					return true;

				// Compare first name and last name of every person with filter
				// text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (customer.getFirstName().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				else if (customer.getCustomerId().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				else if (customer.getLastName().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;

				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<CustomerViewModel> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(customerTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		customerTable.setItems(sortedData);
		
		// 6. Create context menu for selected customer
		customerTable.setRowFactory(
					new Callback<TableView<CustomerViewModel>, TableRow<CustomerViewModel>>() {
						
						@Override
						public TableRow<CustomerViewModel> call(TableView<CustomerViewModel> tableView) {
							final TableRow<CustomerViewModel> row = new TableRow<>();
							final ContextMenu rowMenu = getContextMenuForCustomer(row);
							
						    // only display context menu for non-null items:
						    row.contextMenuProperty().bind(
						      Bindings.when(Bindings.isNotNull(row.itemProperty()))
						      .then(rowMenu)
						      .otherwise((ContextMenu)null));
						    return row;
						}
					}
				);
	}

	private ContextMenu getContextMenuForCustomer(TableRow<CustomerViewModel> selectedRow) {
		ContextMenu contextMenu = new ContextMenu();
		
		MenuItem createBill = new MenuItem("Rechung Erstellen");
		MenuItem updateCustomerData = new MenuItem("Kunden Daten Ändern");

		updateCustomerData.setOnAction(e -> {
			// numberInputWindow.show();
		});

		/*
		 * inputSubmitBtn.setOnAction(e -> {
		 * checkChoisedNumberAndSetArticleNumber(); numberInputWindow.close();
		 * });
		 */

		createBill.setOnAction(e -> {
			CustomerViewModel customerToCreateBill = selectedRow.getItem();
			Launcher.root.setCenter(new CenterArea().loadCenterPane(ButtonOnLeftArea.RECHNUNG, customerToCreateBill));
		});
		contextMenu.getItems().addAll(createBill, updateCustomerData);

		return contextMenu;
	}

	public void transformAndPersist(HashMap<Enum, String> customParameterList) {
		Customer customer = transformer.entityFromParameterList(customParameterList);
		dbService.createCustomer(customer);
	}
}