package com.rechpro.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rechpro.entity.Customer;
import com.rechpro.viewmodel.CustomerViewModel;

import javafx.beans.property.SimpleStringProperty;

public class CustomerTransformer {
	
	public CustomerViewModel entityToViewModel(Customer customer) {
		CustomerViewModel customerVM = new CustomerViewModel();
		customerVM.setCustomerId(new SimpleStringProperty(Integer.toString(customer.getId())));
		customerVM.setSex(new SimpleStringProperty(customer.getSex()));
		customerVM.setFirstName(new SimpleStringProperty(customer.getFirstName()));
		customerVM.setLastName(new SimpleStringProperty(customer.getLastName()));
		customerVM.setStreet(new SimpleStringProperty(customer.getStreet()));
		customerVM.setNo(new SimpleStringProperty(customer.getNo()));
		customerVM.setPostCode(new SimpleStringProperty(customer.getPostCode()));
		customerVM.setCity(new SimpleStringProperty(customer.getCity()));
		customerVM.setCountry(new SimpleStringProperty(customer.getCountry()));
		customerVM.setPhone(new SimpleStringProperty(customer.getPhone()));
		customerVM.setMobilePhone(new SimpleStringProperty(customer.getMobilePhone()));
		customerVM.setFax(new SimpleStringProperty(customer.getFax()));
		customerVM.setEmail(new SimpleStringProperty(customer.getEmail()));
		customerVM.setBirthDay(new SimpleStringProperty(customer.getBirthDay()));
		customerVM.setBankNo(new SimpleStringProperty(customer.getBankNo()));
		customerVM.setBlz(new SimpleStringProperty(customer.getBlz()));
		customerVM.setIban(new SimpleStringProperty(customer.getIban()));
		customerVM.setBicNo(new SimpleStringProperty(customer.getBicNo()));
		
		return customerVM;
	}
	
	public Customer entityFromParameterList(HashMap<Enum, String> customParameterList) {
		return new Customer(customParameterList);
	}

	public List<CustomerViewModel> convertAndGetAllCustomer(List<Customer> customers) {
		List<CustomerViewModel> list = new ArrayList<CustomerViewModel>();
		for (Customer customer : customers) {
			list.add(entityToViewModel(customer));
		}
		return list;
	}

}
