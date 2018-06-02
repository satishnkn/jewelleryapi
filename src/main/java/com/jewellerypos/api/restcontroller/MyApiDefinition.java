package com.jewellerypos.api.restcontroller;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.ReaderListener;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.BasicAuthDefinition;
import io.swagger.models.auth.In;

@SwaggerDefinition
public class MyApiDefinition  implements ReaderListener {

    @Override
    public void beforeScan(Reader reader, Swagger swagger) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterScan(Reader reader, Swagger swagger) {
        BasicAuthDefinition basicAuth = new BasicAuthDefinition();
        ApiKeyAuthDefinition bearerAuth = new ApiKeyAuthDefinition("authorization", In.HEADER);
        
        swagger.addSecurityDefinition("basicAuth", basicAuth);
        swagger.addSecurityDefinition("Bearer", bearerAuth);
        
    }

}
