package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class BillnoNotValidException extends RuntimeException implements Serializable {
	 private static final long serialVersionUID = 1L;

	    public BillnoNotValidException() {
	        super();
	    }
	    
	    public BillnoNotValidException(String msg) {
	        super(msg);
	    }
	    
	    public BillnoNotValidException(String msg,Exception exp) {
	        super(msg,exp);
	    }
	    public BillnoNotValidException(ErrorScenario errorScenario,String param)
	    {
	        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
	    }
	    
	    public BillnoNotValidException(ErrorScenario errorScenario)
	    {
	        super(errorScenario.responseMessage);        
	    }

}
