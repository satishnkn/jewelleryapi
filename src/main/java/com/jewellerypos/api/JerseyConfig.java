package com.jewellerypos.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jewellerypos.api.exceptionmapper.CustomExceptionMapper;
import com.jewellerypos.api.exceptionmapper.CustomValidationExceptionMapper;
import com.jewellerypos.api.exceptionmapper.NotAuthorizedExceptionMapper;
import com.jewellerypos.api.exceptionmapper.NotFoundExceptionMapper;
import com.jewellerypos.api.filter.AuthenticationFilter;
import com.jewellerypos.api.filter.CorsResponseFilter;
import com.jewellerypos.api.model.ChitMember;
import com.jewellerypos.api.restcontroller.ChitGroupController;
import com.jewellerypos.api.restcontroller.ChitMemberController;
import com.jewellerypos.api.restcontroller.ChitTranController;
import com.jewellerypos.api.restcontroller.DealerController;
import com.jewellerypos.api.restcontroller.MetalRateHistoryController;
import com.jewellerypos.api.restcontroller.OperatorController;
import com.jewellerypos.api.restcontroller.ProductController;
import com.jewellerypos.api.restcontroller.PurchaseController;
import com.jewellerypos.api.restcontroller.SaleController;
import com.jewellerypos.api.restcontroller.TagController;


@Configuration
public class JerseyConfig extends ResourceConfig {
    
    public JerseyConfig() {
    	register(CorsResponseFilter.class);
        register(MetalRateHistoryController.class);
        register(ProductController.class);
        register(DealerController.class);
        register(PurchaseController.class);
        register(TagController.class);
        register(ChitGroupController.class);
        register(ChitMemberController.class);
        register(ChitTranController.class);
        register(OperatorController.class);
        register(SaleController.class);
        register(CustomExceptionMapper.class);
        register(CustomValidationExceptionMapper.class);
        register(NotFoundExceptionMapper.class);
        register(NotAuthorizedExceptionMapper.class);
        register(AuthenticationFilter.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

}
