package com.rechpro.persistence.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rechpro.entity.Article;
import com.rechpro.persistence.dao.IArticleDAO;

@Repository
public class ArticleDAO implements IArticleDAO {

	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@Override
	public Article persistOrMerge(Article article) {
		return (Article) this.sessionFactory.getCurrentSession().merge(article);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> findAllArticles() {
		return (List<Article>) this.sessionFactory.getCurrentSession().createQuery(
				"from Article").getResultList();
	}

}
