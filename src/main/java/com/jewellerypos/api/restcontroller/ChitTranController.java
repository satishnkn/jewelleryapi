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
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jewellerypos.api.annotation.Secured;
import com.jewellerypos.api.model.ChitTran;
import com.jewellerypos.api.service.ChitTranService;
import com.jewellerypos.api.util.Role;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "ChitTran", produces = MediaType.APPLICATION_JSON , authorizations = {@Authorization(value="basicAuth")})
public class ChitTranController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChitTranController.class);
    
    private final ChitTranService chitTranService;
    
    @Autowired
    public ChitTranController(ChitTranService chitTranService) {
        this.chitTranService = chitTranService;
    }
    
    @POST    
    @Path("/v1.0/chittran")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public ChitTran createTransaction(@Valid ChitTran chitTran){
        return chitTranService.createTransaction(chitTran);
    }
    
    @PUT    
    @Path("/v1.0/chittran/{tranNo}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Secured({Role.ADMIN,Role.SUPERADMIN})
    public ChitTran updateTransaction(@PathParam("tranNo") long tranNo,@Valid ChitTran chitTran){
        return chitTranService.updateTransaction(tranNo,chitTran);
        
    }
    
    @GET
    @Path("/v1.0/chittran/{chitMemberSno}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public List<ChitTran> getTransactionByMember(@PathParam("chitMemberSno") long chitMemberSno){
        return chitTranService.getTransactionByMember(chitMemberSno);
        
    }
    
    @GET
    @Path("/v1.0/chittran")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public List<ChitTran> getAllTransaction(@QueryParam("page") int page,@QueryParam("size") int size){
        return chitTranService.getAllTransaction(page,size);
        
    }
    
    @GET
    @Path("/v1.0/chittran/tranNo")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public ChitTran getTransactionByTranNo(@PathParam("tranNo") long tranNo){
        return chitTranService.getTransactionByTranNo(tranNo);
        
    }
    
    @PUT    
    @Path("/v1.0/chittran/cancel/{tranNo}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Secured({Role.ADMIN,Role.SUPERADMIN})
    public ChitTran updateTransaction(@PathParam("tranNo") long tranNo){
        return chitTranService.cancelTransaction(tranNo);
        
    }
    
    

}
