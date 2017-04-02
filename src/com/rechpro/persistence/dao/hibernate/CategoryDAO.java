package com.rechpro.persistence.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rechpro.entity.Category;
import com.rechpro.persistence.dao.ICategoryDAO;

@Repository
public class CategoryDAO implements ICategoryDAO {

	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@Override
	public Category findCategoryByName(String name) {
		return (Category) this.sessionFactory.getCurrentSession().createQuery(
				"from Category cat where cat.name=?").setParameter(0, name)
				.uniqueResult();
	}

	@Override
	public Category persistOrMerge(Category category) {
		return (Category) this.sessionFactory.getCurrentSession().merge(category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> retrieveAllCategories() {
		return (List<Category>) this.sessionFactory.getCurrentSession().createQuery(
				"from Category").getResultList();
	}

}
