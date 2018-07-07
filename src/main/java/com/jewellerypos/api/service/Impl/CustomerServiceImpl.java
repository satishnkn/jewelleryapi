package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.model.Customer;
import com.jewellerypos.api.repository.CustomerRepository;
import com.jewellerypos.api.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
		
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		System.out.println("rEACH SERVICE");
		customer.setUpdatedOn(LocalDateTime.now());
		Customer c = customerRepository.save(customer);
		return c;
	}

	@Override
	public Page<Customer> getAllCustomer(int page, int size) {
		Page<Customer> customer ;
		 PageRequest pageRequest = new PageRequest(page, size,
	                new Sort(Sort.Direction.ASC, "custId"));
		customer= customerRepository.findAll(pageRequest);
		
		return customer;
	}

	@Override
	public List<Customer> searchCustomer( String mobile, String emailId, String name) {
		System.out.println(mobile);
		List<Customer> customer = new ArrayList<>();
		if(name != null && emailId != null && mobile != null)
			customer = customerRepository.findByCustNameContainingOrCustEmailContainingOrCustMobileContaining(name,emailId,mobile);
		else if(name != null && emailId != null)
			customer = customerRepository.findByCustNameContainingOrCustEmailContaining(name,emailId);
		else if(name != null && mobile != null)
			customer = customerRepository.findByCustNameContainingOrCustMobileContaining(name,mobile);
		else if(name != null)
			customer = customerRepository.findByCustNameContaining(name);
		else if(emailId != null)
			customer = customerRepository.findByCustEmailContaining(emailId);
		else if(mobile != null)
			customer = customerRepository.findByCustMobileContaining(mobile);
		return customer;
	}

	

}
