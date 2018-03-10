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

import org.hibernate.loader.custom.Return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jewellerypos.api.model.ChitMember;
import com.jewellerypos.api.response.PageChitMemberResponse;
import com.jewellerypos.api.service.ChitMemberService;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ChitMemberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChitMemberController.class);
    
    private final ChitMemberService  chitMemberService;
    
    @Autowired
    public ChitMemberController(ChitMemberService  chitMemberService) {
        this.chitMemberService = chitMemberService ;
    }
    
    @POST    
    @Path("/v1.0/chitmember")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    private ChitMember createChitMember(@Valid ChitMember chitMember){
        return chitMemberService.createMember(chitMember);
        
    }
    
    @PUT   
    @Path("/v1.0/chitmember/{chitmemberId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    private ChitMember updateChitMember(@PathParam("chitmemberId") long chitmemberId, @Valid ChitMember chitMember){
        return chitMemberService.updateMember(chitmemberId,chitMember);
        
    }
    
    @GET
    @Path("/v1.0/chitmember")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    private PageChitMemberResponse getAllChitMember(@QueryParam("page") int page,@QueryParam("size") int size){
        return chitMemberService.getAllChitMember(page,size);
    }
    
    @GET
    @Path("/v1.0/chitmember/{chitmemberId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    private ChitMember getChitMemberById(@PathParam("chitmemberId") long chitmemberId){
        return chitMemberService.getChitMemberById(chitmemberId);
        
        
    }

}
