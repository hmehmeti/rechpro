package com.rechpro.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rechpro.entity.Article;
import com.rechpro.viewmodel.ArticleViewModel;
import com.rechpro.viewmodel.ArticleViewModelInRechnung;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author kdogan
 *
 */

public class ArticleTransformer {

	public ArticleViewModel entityToViewModel(Article article){
		ArticleViewModel articleVM = new ArticleViewModel();
		SimpleStringProperty articleNumber = new SimpleStringProperty(Integer.toString(article.getArticleNumber()));
		SimpleStringProperty name = new SimpleStringProperty(article.getName());
		SimpleStringProperty description = new SimpleStringProperty(article.getDescription());
		SimpleStringProperty rechnungId = new SimpleStringProperty(Integer.toString(article.getRechnungId()));
		SimpleStringProperty categoryName = new SimpleStringProperty(article.getCategory().getName());
		SimpleStringProperty price = new SimpleStringProperty(Double.toString(article.getPrice()));
		
		articleVM.setArticleNumber(articleNumber);
		articleVM.setName(name);
		articleVM.setDescription(description);
		articleVM.setRechnungId(rechnungId);
		articleVM.setCategoryName(categoryName);
		articleVM.setPrice(price);
		
		return articleVM;
	}
	
	/*
	 * @param
	 * 		model - category name of view-model will be read from database, 
	 * 				so there is no need to save the category with this name
	 */
	public Article viewModelToEntity(ArticleViewModel model) {
		int articleName = Integer.parseInt(model.getArticleNumber().get());
		String name = model.getName().get();
		String description = model.getDescription().get();
		int rechnungId = Integer.parseInt(model.getRechnungId().get());
		double price = Double.parseDouble(model.getPrice().get());
		return new Article(articleName, name, description, rechnungId, price);
	}
	
	public Article entityFromParameterList(HashMap<Enum, String> articleParameterList) {
		return new Article(articleParameterList);
	}

	public List<ArticleViewModel> convertAndGetAllArticle(List<Article> articles) {
		List<ArticleViewModel> list = new ArrayList<ArticleViewModel>();
		for (Article article : articles) {
			list.add(entityToViewModel(article));
		}
		return list;
	}
	
	public ArticleViewModelInRechnung articleViewModel2ArticleViewModelInRechnung(ArticleViewModel model){
		int articleName = Integer.parseInt(model.getArticleNumber().get());
		String name = model.getName().get();
		String description = model.getDescription().get();
		int rechnungId = Integer.parseInt(model.getRechnungId().get());
		String categoryName = model.getCategoryName().get();
		double price = Double.parseDouble(model.getPrice().get());
		
		return new ArticleViewModelInRechnung(articleName, name, description, rechnungId, categoryName, price);
	}
}
