package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class BillAlreadyCancelledException extends RuntimeException implements Serializable{

	 private static final long serialVersionUID = 1L;

	    public BillAlreadyCancelledException() {
	        super();
	    }
	    
	    public BillAlreadyCancelledException(String msg) {
	        super(msg);
	    }
	    
	    public BillAlreadyCancelledException(String msg,Exception exp) {
	        super(msg,exp);
	    }
	    public BillAlreadyCancelledException(ErrorScenario errorScenario,String param)
	    {
	        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
	    }
	    
	    public BillAlreadyCancelledException(ErrorScenario errorScenario)
	    {
	        super(errorScenario.responseMessage);        
	    }
}
