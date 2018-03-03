package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class DealerAlreadyExistException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public DealerAlreadyExistException() {
	        super();
	    }
	    
	    public DealerAlreadyExistException(String msg) {
	        super(msg);
	    }
	    
	    public DealerAlreadyExistException(String msg,Exception exp) {
	        super(msg,exp);
	    }
	    public DealerAlreadyExistException(ErrorScenario errorScenario,String param)
	    {
	        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
	    }
	    
	    public DealerAlreadyExistException(ErrorScenario errorScenario)
	    {
	        super(errorScenario.responseMessage);        
	    }



}
