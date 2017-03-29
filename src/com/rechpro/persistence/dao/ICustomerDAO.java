package com.rechpro.persistence.dao;

import java.util.List;

import com.rechpro.entity.Customer;

public interface ICustomerDAO {

	public Customer persistOrMerge(Customer customer);
	public List<Customer> retrieveAllCustomers();
	
}
