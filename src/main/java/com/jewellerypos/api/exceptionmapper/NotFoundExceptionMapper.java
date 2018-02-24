package com.jewellerypos.api.exceptionmapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jewellerypos.api.error.ErrorModel;
import com.jewellerypos.api.error.JPOSErrorConstant;



@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        ErrorModel em=new ErrorModel();        
        em.setCode(JPOSErrorConstant.REQUEST_PATH_NOT_FOUND_ERRORCODE);
        em.setMessage("No operational matching request path is found");
        em.setStatus("Resorce NotFound");
        return Response.status(Status.NOT_FOUND).entity(em).type("application/json").build();
        
        
    
        
    }

}
