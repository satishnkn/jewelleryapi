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
import com.jewellerypos.api.model.Sale;
import com.jewellerypos.api.request.PurchaseRequest;
import com.jewellerypos.api.request.SaleRequest;
import com.jewellerypos.api.response.SaleResponse;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.service.SaleService;

@RestController
@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class SaleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SaleController.class);
	
	private final SaleService saleService;
	
	@Autowired
	public SaleController( SaleService saleService) {
		this.saleService = saleService;
	}
	
	@POST    
    @Path("/v1.0/sale")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public SaleResponse createSales(@Valid SaleRequest saleReq ){
        return saleService.createSales(saleReq);
    }
	
	
	@PUT  
    @Path("/v1.0/sale/cancel/{saleBillno}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public StatusResponse cancelPurchase(@PathParam("saleBillno") long saleBillNo ){
        return saleService.cancelSales(saleBillNo);
        
    }
	
	@GET 
    @Path("/v1.0/sale/{saleBillno}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public List<Sale> getPurchaseByPurchaseBillNo(@PathParam("saleBillno") long saleBillNo ){
        return saleService.getSalesBySaleBillNo(saleBillNo);
        
    }
	
	@GET 
    @Path("/v1.0/sale")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public List<Sale> getPurchaseByPurchaseBillNo(@QueryParam("page") int page,@QueryParam("size") int size ){
        return saleService.getAllSales(page,size);
        
    }
	
	
	
	
	

}
