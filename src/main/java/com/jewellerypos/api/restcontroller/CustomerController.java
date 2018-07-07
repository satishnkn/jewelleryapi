package com.jewellerypos.api.restcontroller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.jewellerypos.api.model.Customer;
import com.jewellerypos.api.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Customer", produces = MediaType.APPLICATION_JSON , authorizations = {@Authorization(value="basicAuth")})
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@POST    
    @Path("/v1.0/customer")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Customer createCustomer(@Valid Customer customer ){
		System.out.println("REACH CONTROLLER");
		return customerService.createCustomer(customer);
		
	}
	
	@GET
    @Path("/v1.0/customer")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Page<Customer> getAllCustomer(@QueryParam("page") int page,@QueryParam("size") int size){
		return customerService.getAllCustomer(page,size);
		
	}
	
	@GET
    @Path("/v1.0/customersearch")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public List<Customer> searchCustomer(@QueryParam("mobile") String mobile,@QueryParam("emailId") String emailId,@QueryParam("name") String name){
		if(mobile != null)
			mobile = "%"+mobile+"%";
		if(emailId != null)
			emailId = "%"+emailId+"%";
		if(name != null)
			name = "%"+name+"%";
		
		return customerService.searchCustomer(mobile,emailId,name);
		
	}

}
