package com.jewellerypos.api.restcontroller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
	
	/*@POST    
    @Path("/v1.0/chitgroup")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })*/
}
