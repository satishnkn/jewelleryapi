package com.jewellerypos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	List<Customer> findByCustNameContainingOrCustEmailContainingOrCustMobileContaining(String name, String emailId, String mobile);

	List<Customer> findByCustNameContaining(String name);

	List<Customer> findByCustNameContainingOrCustEmailContaining(String name, String emailId);

	List<Customer> findByCustNameContainingOrCustMobileContaining(String name, String mobile);

	List<Customer> findByCustEmailContaining(String emailId);

	List<Customer> findByCustMobileContaining(String mobile);

}
