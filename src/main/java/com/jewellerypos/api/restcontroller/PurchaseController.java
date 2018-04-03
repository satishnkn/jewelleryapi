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

import com.jewellerypos.api.model.Purchase;
import com.jewellerypos.api.request.ProductRequest;
import com.jewellerypos.api.request.PurchaseRequest;
import com.jewellerypos.api.response.PagePurchaseResponse;
import com.jewellerypos.api.response.ProductRepsonse;
import com.jewellerypos.api.response.PurchaseResponse;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.service.PurchaseService;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class PurchaseController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);
	 
	 private final PurchaseService purchaseService;
	 
	 @Autowired
	 public PurchaseController(PurchaseService purchaseService) {
		 this.purchaseService = purchaseService;
	}
	 
	@POST    
    @Path("/v1.0/purchase")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public PurchaseResponse createPurchase(@Valid PurchaseRequest purchaseReq ){
        return purchaseService.createPurchase(purchaseReq);
        
    }
	
	@PUT  
    @Path("/v1.0/purchase/{purchaseBillno}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public PurchaseResponse updatePurchase(@PathParam("purchaseBillno") long purchaseBillNo, @Valid PurchaseRequest purchaseReq ){
        return purchaseService.updatePurchase(purchaseBillNo,purchaseReq);
        
    }
	
	@PUT  
    @Path("/v1.0/purchase/cancel/{purchaseBillno}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public StatusResponse cancelPurchase(@PathParam("purchaseBillno") long purchaseBillNo ){
        return purchaseService.cancelPurchase(purchaseBillNo);
        
    }
	
	
	@GET 
    @Path("/v1.0/purchase/{purchaseBillno}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public List<Purchase> getPurchaseByPurchaseBillNo(@PathParam("purchaseBillno") long purchaseBillNo ){
        return purchaseService.getPurchaseByPurchaseBillNo(purchaseBillNo);
        
    }
	
	
	@GET 
    @Path("/v1.0/purchase")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public PagePurchaseResponse getAllPurchase(@QueryParam("page") int page,@QueryParam("size") int size){
        return purchaseService.getAllPurchase(page,size);
        
    }
	
	
	@GET 
    @Path("/v1.0/purchasevstag")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public PagePurchaseResponse getPurchasevsTag(@QueryParam("page") int page,@QueryParam("size") int size){
        return purchaseService.getPurchasevsTag(page,size);
        
    }
	

}
