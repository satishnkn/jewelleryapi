package com.jewellerypos.api.restcontroller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jewellerypos.api.model.SalesFormula;
import com.jewellerypos.api.request.ProductRequest;
import com.jewellerypos.api.request.SalesFormulaRequest;
import com.jewellerypos.api.response.ProductRepsonse;
import com.jewellerypos.api.service.SalesFormulaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "SalesFormula", produces = MediaType.APPLICATION_JSON , authorizations = {@Authorization(value="basicAuth")})
public class SalesFormulaController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesFormulaController.class);
	
	 private final SalesFormulaService salesFormulaService;
	
	@Autowired
	public SalesFormulaController(SalesFormulaService salesFormulaService) {
		this.salesFormulaService = salesFormulaService;
	}
	
	 @POST    
	    @Path("/v1.0/salesformula")
	    @Consumes({ "application/json" })
	    @Produces({ "application/json" })
	    //@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
	    public SalesFormula createFormula(@Valid SalesFormulaRequest salesFormulaRequest ){
	        return salesFormulaService.createFormula(salesFormulaRequest);
	         
	    }
	 
	 @GET    
	    @Path("/v1.0/salesformula")
	    @Consumes({ "application/json" })
	    @Produces({ "application/json" })
	 public List<SalesFormula> getAllFormula(){
		 return salesFormulaService.findAllFormula();
	 }

}
