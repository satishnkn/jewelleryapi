package com.jewellerypos.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.model.Customer;


public interface CustomerService {

	public Customer createCustomer(Customer customer);

	public Page<Customer> getAllCustomer(int page, int size);

	public List<Customer> searchCustomer(String mobile, String emailId, String name);


}
