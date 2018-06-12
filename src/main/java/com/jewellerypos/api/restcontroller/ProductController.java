package com.jewellerypos.api.restcontroller;

import java.util.ArrayList;
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
import com.jewellerypos.api.model.Product;
import com.jewellerypos.api.request.ProductRequest;
import com.jewellerypos.api.response.PageProductResposne;
import com.jewellerypos.api.response.ProductRepsonse;
import com.jewellerypos.api.service.ProductService;
import com.jewellerypos.api.util.Role;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;



@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(value = "Product", produces = MediaType.APPLICATION_JSON , authorizations = {@Authorization(value="basicAuth")})
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @POST    
    @Path("/v1.0/product")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public ProductRepsonse createProduct(@Valid ProductRequest proReq ){
        return productService.createProduct(proReq);
        
    }
    
    @PUT   
    @Path("/v1.0/product/{productCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.SUPERADMIN})
    public ProductRepsonse updateProduct(@PathParam("productCode") long productCode,@Valid ProductRequest proReq ){
        return productService.updateProduct(productCode,proReq);
        
    }
    
    @GET
    @Path("/v1.0/product")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public PageProductResposne getAllProduct(@QueryParam("page") int page,@QueryParam("size") int size){
        return productService.getAllProduct(page,size);
        
    }
    
    @GET
    @Path("/v1.0/product/{productCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    //@Secured({Role.ADMIN,Role.OPERATOR,Role.SUPERADMIN})
    public ProductRepsonse getProductByCode(@PathParam("productCode") long productCode){
        return productService.getProductByCode(productCode);
        
    }
    
    

}
