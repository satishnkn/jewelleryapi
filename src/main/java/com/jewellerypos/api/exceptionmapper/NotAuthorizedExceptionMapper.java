package com.jewellerypos.api.exceptionmapper;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jewellerypos.api.error.ErrorModel;
import com.jewellerypos.api.error.JPOSErrorConstant;
import com.jewellerypos.api.error.NotAuthorizedException;


@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {

    @Override
    public Response toResponse(NotAuthorizedException exception) {
        ErrorModel em=new ErrorModel();        
        System.err.println("Error Message ::"+exception.getMessage());
        String[] errMsg = exception.getMessage().split("-");
        em.setCode(errMsg[0]);
        em.setMessage(errMsg[1]);
        em.setStatus("UnAuthorized Acess");
        return Response.status(Status.UNAUTHORIZED).entity(em).type("application/json").build();
        
        
       /* ErrorModel em=new ErrorModel();        
        em.setCode(JPOSErrorConstant.REQUEST_PATH_NOT_FOUND_ERRORCODE);
        em.setMessage("No operational matching request path is found");
        em.setStatus("Resorce NotFound");
        return Response.status(Status.NOT_FOUND).entity(em).type("application/json").build();*/
        
    }

}
