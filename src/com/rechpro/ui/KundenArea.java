package com.rechpro.ui;

import com.rechpro.persistence.Address;
import com.rechpro.persistence.Customer;
import com.rechpro.ui.EditingCell;
import com.rechpro.ui.IFormRechnung;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 * @author hmehmeti
 *
 */
public class KundenArea {
	/**
	 * TODO HME ist noch zu implementieren
	 */
	public KundenArea() {
	}

	TableView<Customer> table = new TableView<Customer>();
	private Address gundiAddress = new Address("Gundistr.", "12", "44488", "Karlsruhe", "Deutschland");
	private Address maxAddress = new Address("Gundistr.", "12", "44488", "Stuttgart", "Deutschland");
	private Address tayipAddress = new Address("Gundistr.", "12", "44488", "Dortmund", "Deutschland");
	private Address merkelAddress = new Address("Gundistr.", "12", "44488", "Karlsruhe", "Deutschland");
	final ObservableList<Customer> data = FXCollections.observableArrayList(
			new Customer("Gundi", "Gundieren", "gundi.gundieren@gundi.com", gundiAddress),
			new Customer("Max", "Mustermann", "max.mustermann@example.com", maxAddress),
			new Customer("Tayip", "Merkel", "tayip.merkel@kilimili.com", tayipAddress),
			new Customer("Putin", "Obama", "putin.obama@freunde.com", merkelAddress));

	final HBox hb = new HBox();

	protected VBox getTableViewKunde() {

		final Label label = new Label(IFormRechnung.LBL_TABLE_ADDRESS_CUSTOMERS);
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);
		Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};

		TableColumn firstNameCol = createTableColumn(IFormRechnung.LBL_FIRST_NAME);
		firstNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
		firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
			}
		});

		TableColumn lastNameCol = createTableColumn(IFormRechnung.LBL_LAST_NAME);
		lastNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
		lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
			}
		});

		TableColumn emailCol = createTableColumn(IFormRechnung.LBL_EMAIL);
		emailCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			}
		});

		TableColumn addresseCol = createTableColumn(IFormRechnung.LBL_ADDRESSE);
		addresseCol.prefWidthProperty().bind(table.widthProperty().multiply(0.35));
		addresseCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(spliteAddressAndGetAddressObj(t.getNewValue()));
			}
			private Address spliteAddressAndGetAddressObj(String addressString) {
				String[] stringArray = addressString.split("\\s+");
				String street = stringArray[0];
				String no = stringArray[1];
				String postCode = stringArray[2];
				String city = stringArray[3];
				String country = stringArray[4];
				return new Address(street, no ,postCode, city, country);
			}
		});

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, addresseCol);

		final TextField addFirstName = new TextField();
		addFirstName.setPromptText(IFormRechnung.LBL_FIRST_NAME);
		addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
		final TextField addLastName = new TextField();
		addLastName.setMaxWidth(lastNameCol.getPrefWidth());
		addLastName.setPromptText(IFormRechnung.LBL_LAST_NAME);
		final TextField addEmail = new TextField();
		addEmail.setMaxWidth(emailCol.getPrefWidth());
		addEmail.setPromptText(IFormRechnung.LBL_EMAIL);
		final TextField addAddress = new TextField();
		addAddress.setMaxWidth(emailCol.getPrefWidth());
		addAddress.setPromptText(IFormRechnung.LBL_ADDRESSE);

		final Button addButton = new Button(IFormRechnung.BTN_KUNDEN_ADD);

		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.add(new Customer(addFirstName.getText(), addLastName.getText(), addEmail.getText(), spliteAddressAndGetAddressObj(addAddress.getText())));
				addFirstName.clear();
				addLastName.clear();
				addEmail.clear();
				addAddress.clear();
			}

			private Address spliteAddressAndGetAddressObj(String addressString) {
				
				String[] stringArray = addressString.split("\\s+");
				String street = stringArray[0];
				String no = stringArray[1];
				String postCode = stringArray[2];
				String city = stringArray[3];
				String country = stringArray[4];
				return new Address(street, no ,postCode, city, country);
			}
		});

		hb.getChildren().addAll(addFirstName, addLastName, addEmail, addAddress, addButton);
		hb.setSpacing(4);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);
		return vbox;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private TableColumn createTableColumn(String columnName) {
		Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};
		TableColumn column = new TableColumn(columnName);
		column.setCellValueFactory(new PropertyValueFactory<Customer, String>(columnName));
		column.setCellFactory(cellFactory);
		
		return column;
		
	}
}
