package com.rechpro.ui;

import com.rechpro.viewmodel.ArticleViewModelInRechnung;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableGenerator {

	@SuppressWarnings("unchecked")
	public TableView<ArticleViewModelInRechnung> getSelectedArticleTable() {
		TableView<ArticleViewModelInRechnung> articleTable = new TableView<ArticleViewModelInRechnung>();
		
		TableColumn<ArticleViewModelInRechnung,String> articleNumber = new TableColumn<ArticleViewModelInRechnung,String>("Artikel Nr.");
		articleNumber.setCellValueFactory(new PropertyValueFactory<ArticleViewModelInRechnung,String>("articleNumber"));
		articleNumber.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.20));
		
		TableColumn<ArticleViewModelInRechnung,String> name = new TableColumn<ArticleViewModelInRechnung,String>("Artikel");
		name.setCellValueFactory(new PropertyValueFactory<ArticleViewModelInRechnung,String>("name"));
		name.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.40));
		//TODO: get number of article
        TableColumn<ArticleViewModelInRechnung,String> amountOfItems = new TableColumn<ArticleViewModelInRechnung,String>("Anzahl");
        amountOfItems.setCellValueFactory(new PropertyValueFactory<ArticleViewModelInRechnung,String>("amount"));
        amountOfItems.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.10));
        
        TableColumn<ArticleViewModelInRechnung,String> onePrice = new TableColumn<ArticleViewModelInRechnung,String>("Einzel Preis");
        onePrice.setCellValueFactory(new PropertyValueFactory<ArticleViewModelInRechnung,String>("price"));
        onePrice.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.15));
       
        TableColumn<ArticleViewModelInRechnung,String> entirePrice = new TableColumn<ArticleViewModelInRechnung,String>("Gesamt Preis");
        entirePrice.setCellValueFactory(new PropertyValueFactory<ArticleViewModelInRechnung,String>("totalPrice"));
        entirePrice.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.15));
        
        articleTable.getColumns().addAll(articleNumber, name, amountOfItems, onePrice, entirePrice);
        
		return articleTable;
	}

}
