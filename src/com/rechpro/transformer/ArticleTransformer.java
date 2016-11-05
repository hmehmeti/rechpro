package com.rechpro.transformer;

import java.util.ArrayList;
import java.util.List;

import com.rechpro.entity.Article;
import com.rechpro.viewmodel.ArticleViewModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author kdogan
 *
 */

public class ArticleTransformer {

	public ArticleViewModel entityToViewModel (Article article){
		ArticleViewModel articleVM = new ArticleViewModel();
		SimpleStringProperty articleNumber = new SimpleStringProperty(Integer.toString(article.getArticleNumber()));
		SimpleStringProperty name = new SimpleStringProperty(article.getName());
		SimpleStringProperty description = new SimpleStringProperty(article.getDescription());
		SimpleStringProperty rechnungId = new SimpleStringProperty(Integer.toString(article.getRechnungId()));
		SimpleStringProperty category = new SimpleStringProperty(Integer.toString(article.getCategory()));
		SimpleStringProperty prise = new SimpleStringProperty(Double.toString(article.getPrise()));
		
		articleVM.setArticleNumber(articleNumber);
		articleVM.setName(name);
		articleVM.setDescription(description);
		articleVM.setRechnungId(rechnungId);
		articleVM.setCategory(category);
		articleVM.setPrise(prise);
		
		return articleVM;
	}
	
	public Article entityFromParameterList(int articleNumber, String name, String description, int rechnungId, int category, double prise) {
		return new Article(articleNumber, name, description, rechnungId, category, prise);
	}

	public List<ArticleViewModel> convertAndGetAllArticle(List<Article> articles) {
		List<ArticleViewModel> list = new ArrayList<ArticleViewModel>();
		for (Article article : articles) {
			list.add(entityToViewModel(article));
		}
		return list;
	}
}
