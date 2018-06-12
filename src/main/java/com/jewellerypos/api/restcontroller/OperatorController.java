package com.jewellerypos.api.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jewellerypos.api.annotation.Secured;
import com.jewellerypos.api.model.Operator;
import com.jewellerypos.api.request.LoginRequest;
import com.jewellerypos.api.request.PasswordChangeRequest;
import com.jewellerypos.api.request.ProductRequest;
import com.jewellerypos.api.response.AuthStatusResponse;
import com.jewellerypos.api.response.PageOperatorResponse;
import com.jewellerypos.api.response.PageProductResposne;
import com.jewellerypos.api.response.ProductRepsonse;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.service.OperatorService;
import com.jewellerypos.api.util.PasscodeEncryptorUtil;
import com.jewellerypos.api.util.Role;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Operator", produces = MediaType.APPLICATION_JSON , authorizations = {@Authorization(value="basicAuth")})
public class OperatorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatorController.class);
    
    private final OperatorService operatorService;
    
    @Autowired
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }
    
    @POST    
    @Path("/v1.0/operator")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.SUPERADMIN})
    public Operator createOperator(@Valid Operator operator,@Context HttpServletRequest httpservletRequest ){
        System.out.println("Reached the method");
        long creator = PasscodeEncryptorUtil.getCreatorId(httpservletRequest);
        return operatorService.createOperator(operator,creator);
        
    }
    
    @PUT   
    @Path("/v1.0/operator/{operatorCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.SUPERADMIN})
    public Operator updateOperator(@PathParam("operatorCode") long operatorCode,@Valid Operator operator ){
        return operatorService.updateOperator(operatorCode,operator);
        
    }
    
    
    @GET
    @Path("/v1.0/operator")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public PageOperatorResponse getAllOperator(@QueryParam("page") int page,@QueryParam("size") int size){
        return operatorService.getAllOperator(page,size);
        
    }
    
    @GET
    @Path("/v1.0/operator/{operatorCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.OPERATOR})
    public Operator getOperatorByCode(@PathParam("operatorCode") long operatorCode){
        return operatorService.getOperatorByCode(operatorCode);
        
    }
    
    @POST    
    @Path("/v1.0/login")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public AuthStatusResponse login(@Valid LoginRequest loginReq){
        return operatorService.login(loginReq);
        
    }
    
    @POST    
    @Path("/v1.0/changepassword")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.OPERATOR})
    public AuthStatusResponse passwordChange(@Valid PasswordChangeRequest req){
        return operatorService.passwordChange(req);
        
    }
    
    
    

}
