package com.rechpro.persistence.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rechpro.entity.Customer;
import com.rechpro.persistence.dao.ICustomerDAO;

@Repository
public class CustomerDAO implements ICustomerDAO {

	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@Override
	public Customer persistOrMerge(Customer customer) {
		return (Customer) this.sessionFactory.getCurrentSession().merge(customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> retrieveAllCustomers() {
		return (List<Customer>) this.sessionFactory.getCurrentSession().createQuery(
				"from Customer").getResultList();
	}

}
