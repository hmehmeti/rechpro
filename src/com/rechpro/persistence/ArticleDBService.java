package com.rechpro.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rechpro.entity.Article;
import com.rechpro.persistence.dao.hibernate.ArticleDAO;

@Service
public class ArticleDBService implements IArticleDBService {

	@Autowired(required=true)
	private ArticleDAO articleDao;
	
	@Transactional
	@Override
	public Article createArticle(Article article) {
		return this.articleDao.persistOrMerge(article);
	}

	@Transactional
	@Override
	public List<Article> retrieveAllArticles() {
		return this.articleDao.findAllArticles();
	}
	
}
