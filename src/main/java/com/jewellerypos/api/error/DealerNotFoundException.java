package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class DealerNotFoundException extends RuntimeException implements Serializable{
	
	 /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DealerNotFoundException() {
        super();
    }
    
    public DealerNotFoundException(String msg) {
        super(msg);
    }
    
    public DealerNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public DealerNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public DealerNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }


}
