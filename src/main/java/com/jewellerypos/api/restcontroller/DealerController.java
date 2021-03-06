package com.jewellerypos.api.restcontroller;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jewellerypos.api.annotation.Secured;
import com.jewellerypos.api.model.Dealer;
import com.jewellerypos.api.request.DealerRequest;
import com.jewellerypos.api.request.ProductRequest;
import com.jewellerypos.api.response.PageDealerResponse;
import com.jewellerypos.api.response.ProductRepsonse;
import com.jewellerypos.api.service.DealerService;
import com.jewellerypos.api.util.Role;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Dealer", produces = MediaType.APPLICATION_JSON , authorizations = {@Authorization(value="basicAuth")})
public class DealerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DealerController.class);
	
	private final DealerService dealerService;
	
	@Autowired
	public DealerController(DealerService dealerService) {
		this.dealerService = dealerService;
	}
	
	@POST    
    @Path("/v1.0/dealer")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	//@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public Dealer createDealer(@Valid DealerRequest dealerReq ){
        return dealerService.createDealer(dealerReq);
        
    }
	
	@PUT   
    @Path("/v1.0/dealer/{dealerId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	//@Secured({Role.ADMIN,Role.SUPERADMIN})
    public Dealer updateDealer(@PathParam("dealerId") long dealerId, @Valid DealerRequest dealerReq ){
        return dealerService.updateDealer(dealerId,dealerReq);
    }
	
	@GET   
    @Path("/v1.0/dealer")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	//@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public PageDealerResponse getAllDealer(@QueryParam("page") int page,@QueryParam("size") int size){
        return dealerService.getAllDealer(page, size);
    }
	
	@GET   
    @Path("/v1.0/dealer/{dealerId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	//@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public Dealer getDealerById(@PathParam("dealerId") long dealerId){
        return dealerService.getDealerById(dealerId);
    }
	
	
	

}
