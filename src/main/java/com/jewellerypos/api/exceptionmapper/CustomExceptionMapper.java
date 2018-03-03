package com.jewellerypos.api.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jewellerypos.api.error.BillAlreadyCancelledException;
import com.jewellerypos.api.error.BillnoNotValidException;
import com.jewellerypos.api.error.DealerAlreadyExistException;
import com.jewellerypos.api.error.DealerNotFoundException;
import com.jewellerypos.api.error.ErrorModel;
import com.jewellerypos.api.error.JPOSErrorConstant;
import com.jewellerypos.api.error.MetalNotFoundException;
import com.jewellerypos.api.error.ProductAlreadyExistException;
import com.jewellerypos.api.error.ProductNotFoundException;
import com.jewellerypos.api.error.RateNotUpdatedException;






/**
 * This class used to map Java exceptions to Response.
 */
@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception>{

	/**
	 * This method will return response object which contains cause of runtime failure happened to clients request.
	 * Map an exception to a Response.
	 * @param exception An exception object that will have the cause of runtime failure for mapping.
	 * @return a response mapped from the supplied exception
	 */
	public Response toResponse(Exception exception) {
		
		ErrorModel em=new ErrorModel();

		if (exception instanceof MetalNotFoundException) {
			em.setCode(JPOSErrorConstant.METAL_NOT_FOUND_ERRORCODE);
    		em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
    		em.setStatus("Bad Request");
		}	
		else if (exception instanceof RateNotUpdatedException) {
            em.setCode(JPOSErrorConstant.RATE_NOT_UPDATED_ERRORCODE);
            em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
            em.setStatus("Bad Request");
        }
		else if (exception instanceof ProductAlreadyExistException) {
            em.setCode(JPOSErrorConstant.PRODUCTNAME_ALREADY_EXIST_ERRORCODE);
            em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
            em.setStatus("Bad Request");
        }
		else if (exception instanceof ProductNotFoundException) {
            em.setCode(JPOSErrorConstant.PRODUCT_NOT_FOUND_ERRORCODE);
            em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
            em.setStatus("Bad Request");
        }
		else if (exception instanceof DealerNotFoundException) {
            em.setCode(JPOSErrorConstant.DEALER_NOT_FOUND_ERRORCODE);
            em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
            em.setStatus("Bad Request");
        }
		else if (exception instanceof DealerAlreadyExistException) {
            em.setCode(JPOSErrorConstant.DEALER_ALREADY_EXIST_ERRORCODE);
            em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
            em.setStatus("Bad Request");
        }
		else if (exception instanceof BillnoNotValidException) {
            em.setCode(JPOSErrorConstant.BILLNO_NOT_VALID_ERRORCODE);
            em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
            em.setStatus("Bad Request");
        }
		else if (exception instanceof BillAlreadyCancelledException) {
            em.setCode(JPOSErrorConstant.BILL_ALREADY_CANCELED_ERRORCODE);
            em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
            em.setStatus("Bad Request");
        }
		else {			
			em.setCode(JPOSErrorConstant.UNKNOWN_ERRORCODE);
    		em.setMessage(exception.getClass().toString()+"  "+exception.getMessage());
    		em.setStatus("Unknown Exception");
    		
		}
    	
		return Response.status(Status.BAD_REQUEST).entity(em).build();
	}

}
