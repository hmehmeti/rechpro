package com.rechpro.ui;

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
	final ObservableList<Customer> data = FXCollections.observableArrayList(
			new Customer("Gundi", "Gundieren", "gundi.gundieren@gundi.com", "Karlsruhe"),
			new Customer("Max", "Mustermann", "max.mustermann@example.com", "Karlsruhe"),
			new Customer("Tayip", "Merkel", "tayip.merkel@kilimili.com", "Karlsruhe"),
			new Customer("Putin", "Obama", "putin.obama@freunde.com", "Karlsruhe"));

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
		firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
			}
		});

		TableColumn lastNameCol = createTableColumn(IFormRechnung.LBL_LAST_NAME);
		lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
			}
		});

		TableColumn emailCol = createTableColumn(IFormRechnung.LBL_EMAIL);
		emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			}
		});

		TableColumn addresseCol = createTableColumn(IFormRechnung.LBL_ADDRESSE);
		addresseCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(t.getNewValue());
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
				data.add(new Customer(addFirstName.getText(), addLastName.getText(), addEmail.getText(), addAddress.getText()));
				addFirstName.clear();
				addLastName.clear();
				addEmail.clear();
				addAddress.clear();
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
		column.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		column.setCellValueFactory(new PropertyValueFactory<Customer, String>(columnName));
		column.setCellFactory(cellFactory);
		
		return column;
		
	}
}
