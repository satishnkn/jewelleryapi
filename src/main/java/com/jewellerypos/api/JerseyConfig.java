package com.jewellerypos.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jewellerypos.api.exceptionmapper.CustomExceptionMapper;
import com.jewellerypos.api.exceptionmapper.CustomValidationExceptionMapper;
import com.jewellerypos.api.exceptionmapper.NotFoundExceptionMapper;
import com.jewellerypos.api.restcontroller.DealerController;
import com.jewellerypos.api.restcontroller.MetalRateHistoryController;
import com.jewellerypos.api.restcontroller.ProductController;
import com.jewellerypos.api.restcontroller.PurchaseController;
import com.jewellerypos.api.restcontroller.TagController;


@Configuration
public class JerseyConfig extends ResourceConfig {
    
    public JerseyConfig() {
        register(MetalRateHistoryController.class);
        register(ProductController.class);
        register(DealerController.class);
        register(PurchaseController.class);
        register(TagController.class);
        register(CustomExceptionMapper.class);
        register(CustomValidationExceptionMapper.class);
        register(NotFoundExceptionMapper.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

}
