package com.rechpro.worker;

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
        TableColumn<ArticleViewModelInRechnung,String> numberOfItem = new TableColumn<ArticleViewModelInRechnung,String>("Anzahl");
        numberOfItem.setCellValueFactory(new PropertyValueFactory<ArticleViewModelInRechnung,String>("number"));
        numberOfItem.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.10));
        
        TableColumn<ArticleViewModelInRechnung,String> onePrise = new TableColumn<ArticleViewModelInRechnung,String>("Einzel Preis");
        onePrise.setCellValueFactory(new PropertyValueFactory<ArticleViewModelInRechnung,String>("prise"));
        onePrise.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.15));
       
        TableColumn<ArticleViewModelInRechnung,String> entirePrise = new TableColumn<ArticleViewModelInRechnung,String>("Gesamt Preis");
        entirePrise.setCellValueFactory(new PropertyValueFactory<ArticleViewModelInRechnung,String>("totalPrise"));
        entirePrise.prefWidthProperty().bind(articleTable.widthProperty().multiply(0.15));
        
        articleTable.getColumns().addAll(articleNumber, name, numberOfItem, onePrise, entirePrise);
        
		return articleTable;
	}

}
