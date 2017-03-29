package com.rechpro.persistence;

import java.util.List;

import com.rechpro.entity.Article;

public interface IArticleDBService {

	public Article createArticle(Article article);
	public List<Article> retrieveAllArticles();
	
}
