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

import com.jewellerypos.api.annotation.Secured;
import com.jewellerypos.api.model.Metal;
import com.jewellerypos.api.model.MetalRateHistory;
import com.jewellerypos.api.request.MetalRateHistoryRequest;
import com.jewellerypos.api.service.MetalRateHistoryService;
import com.jewellerypos.api.util.Role;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Metalrate", produces = MediaType.APPLICATION_JSON , authorizations = {@Authorization(value="basicAuth")})
public class MetalRateHistoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetalRateHistoryController.class);
    
    private final MetalRateHistoryService metalRateHistoryService;
    
    @Autowired
    public MetalRateHistoryController(MetalRateHistoryService metalRateHistoryService) {
        this.metalRateHistoryService = metalRateHistoryService;
    }
    
    @POST
   // @ApiOperation(value = "Create MetalRate " , produces = "application/json" ,  authorizations = {@Authorization(value="basicAuth")})
    @Path("/v1.0/metalrate")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.SUPERADMIN})
    public MetalRateHistory createRateHistory(@Valid MetalRateHistoryRequest metalRateRequest){
        return metalRateHistoryService.createHistory(metalRateRequest);
        
    }
    
    @GET
    @Path("/v1.0/metalrate")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public List<Metal> getAllMetalRate(){
    	return metalRateHistoryService.getAllMetalRate();
    }
    
    
    @POST
    @Path("/v1.0/metal")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.SUPERADMIN})
    public Metal createMetal(@Valid Metal metal){
    	return metalRateHistoryService.createMetal(metal);
    }
    
    

}
