package com.jewellerypos.api.restcontroller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jewellerypos.api.model.ChitGroup;
import com.jewellerypos.api.service.ChitGroupService;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ChitGroupController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChitGroupController.class);

	private final ChitGroupService chitGroupService;
	
	@Autowired
	public ChitGroupController(ChitGroupService chitGroupService) {
		this.chitGroupService = chitGroupService;
	}
	
	@POST    
    @Path("/v1.0/chitgroup")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	private ChitGroup createChitGroup(@Valid ChitGroup chitGroup){
	    return chitGroupService.createChitGroup(chitGroup);
	}
	
	@PUT
    @Path("/v1.0/chitgroup/{chitgroupId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	private ChitGroup updateChitGroup(@PathParam("chitgroupId") long chitgroupId,@Valid ChitGroup chitGroup) {
	    return chitGroupService.updateChitGroup(chitgroupId,chitGroup);
	}
	
	@GET
    @Path("/v1.0/chitgroup")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	private List<ChitGroup> getAllChitGroups(@QueryParam("status") String status){
        return chitGroupService.getAllChitGroup(status);
	    
	}
	
	@GET
    @Path("/v1.0/chitgroup/{chitgroupId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    private ChitGroup getChitGroupByChitGroupId(@PathParam("chitgroupId") long chitgroupId){
        return chitGroupService.getChitGroupByChitGroupId(chitgroupId);
    }
	
	
	
	
}
