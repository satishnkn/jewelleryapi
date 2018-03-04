package com.jewellerypos.api.restcontroller;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jewellerypos.api.model.Tag;
import com.jewellerypos.api.request.PurchaseRequest;
import com.jewellerypos.api.response.PurchaseResponse;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.service.TagService;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class TagController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TagController.class);
	
	private final TagService tagService;
	
	@Autowired
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	
	@POST    
    @Path("/v1.0/tag")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public StatusResponse createTag(@Valid Tag tagReq ){
        return tagService.createTag(tagReq);
        
    }
	
	@PUT    
    @Path("/v1.0/tag/{tagId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public StatusResponse updateTag(@PathParam("tagId") String tagId, @Valid Tag tagReq ){
        return tagService.updateTag(tagId,tagReq);
        
        
    }
	
	
	
	

}