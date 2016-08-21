package com.rechpro.ui;

import com.rechpro.persistence.Kunde;

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

	TableView<Kunde> table = new TableView<Kunde>();
	final ObservableList<Kunde> data = FXCollections.observableArrayList(
			new Kunde("Gundi", "Gundieren", "gundi.gundieren@gundi.com", "Karlsruhe"),
			new Kunde("Max", "Mustermann", "max.mustermann@example.com", "Karlsruhe"),
			new Kunde("Tayip", "Merkel", "tayip.merkel@kilimili.com", "Karlsruhe"),
			new Kunde("Putin", "Obama", "putin.obama@freunde.com", "Karlsruhe"));

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

		TableColumn firstNameCol = new TableColumn(IFormRechnung.LBL_FIRST_NAME);
		firstNameCol.setMinWidth(100);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Kunde, String>("firstName"));
		firstNameCol.setCellFactory(cellFactory);
		firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Kunde, String>>() {
			@Override
			public void handle(CellEditEvent<Kunde, String> t) {
				((Kunde) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
			}
		});

		TableColumn lastNameCol = new TableColumn(IFormRechnung.LBL_LAST_NAME);
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Kunde, String>("lastName"));
		lastNameCol.setCellFactory(cellFactory);
		lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Kunde, String>>() {
			@Override
			public void handle(CellEditEvent<Kunde, String> t) {
				((Kunde) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
			}
		});

		TableColumn emailCol = new TableColumn(IFormRechnung.LBL_EMAIL);
		emailCol.setMinWidth(200);
		emailCol.setCellValueFactory(new PropertyValueFactory<Kunde, String>("email"));
		emailCol.setCellFactory(cellFactory);
		emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Kunde, String>>() {
			@Override
			public void handle(CellEditEvent<Kunde, String> t) {
				((Kunde) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			}
		});

		TableColumn addresseCol = new TableColumn(IFormRechnung.LBL_ADDRESSE);
		addresseCol.setMinWidth(200);
		addresseCol.setCellValueFactory(new PropertyValueFactory<Kunde, String>("addresse"));
		addresseCol.setCellFactory(cellFactory);
		addresseCol.setOnEditCommit(new EventHandler<CellEditEvent<Kunde, String>>() {
			@Override
			public void handle(CellEditEvent<Kunde, String> t) {
				((Kunde) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(t.getNewValue());
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
				data.add(new Kunde(addFirstName.getText(), addLastName.getText(), addEmail.getText(), addAddress.getText()));
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
}
