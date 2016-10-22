package com.rechpro.persistence;

import java.util.List;

import com.rechpro.entity.Customer;
import com.rechpro.persistence.HibernateUtil;

public class CustomerDBService {

	public void addCustomer(Customer customer) {
		HibernateUtil.executeHibernateTransaction("add", customer);
	}
	
	public void removeCustomer(Customer customer) {
		HibernateUtil.executeHibernateTransaction("remove", customer);
	}
	
	public void updateCustomer(Customer customer) {
		HibernateUtil.executeHibernateTransaction("update", customer);
	}
	
	public List<Customer> getCustomers() {
		HibernateUtil<Customer> cutomerHibernateUtil = new HibernateUtil<>();
		List<Customer> customers = cutomerHibernateUtil.getObjectsWithHibernateTransaction("Customer");
		return customers;
	}

}
