package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class RateNotUpdatedException extends RuntimeException implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    

    public RateNotUpdatedException() {
        super();
    }
    
    public RateNotUpdatedException(String msg) {
        super(msg);
    }
    
    public RateNotUpdatedException(String msg,Exception exp) {
        super(msg,exp);
    }
    public RateNotUpdatedException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public RateNotUpdatedException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }

}
