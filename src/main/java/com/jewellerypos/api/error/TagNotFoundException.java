package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class TagNotFoundException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TagNotFoundException() {
        super();
    }
    
    public TagNotFoundException(String msg) {
        super(msg);
    }
    
    public TagNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public TagNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public TagNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }


}
