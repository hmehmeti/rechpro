package com.rechpro.persistence;

import java.util.List;

import com.rechpro.entity.Customer;

public interface ICustomerDBService {

	public Customer createCustomer(Customer customer);
	public List<Customer> retrieveAllCustomers();
	
}
