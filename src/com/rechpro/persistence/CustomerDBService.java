package com.rechpro.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rechpro.entity.Customer;
import com.rechpro.persistence.dao.hibernate.CustomerDAO;

@Service("customerDBService")
public class CustomerDBService implements ICustomerDBService {

	@Autowired(required=true)
	private CustomerDAO customerDao;
	
	@Transactional
	public Customer createCustomer(Customer customer) {
		return this.customerDao.persistOrMerge(customer);
	}

	@Transactional
	public List<Customer> retrieveAllCustomers() {
		return this.customerDao.retrieveAllCustomers();
	}

}
