package com.rechpro.persistence.dao;

import java.util.List;

import com.rechpro.entity.Article;

public interface IArticleDAO {

	public Article persistOrMerge(Article article);
	public List<Article> retrieveAllArticles();
	
}
