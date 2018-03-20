package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class OperatorNotFoundException extends RuntimeException implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public OperatorNotFoundException() {
        super();
    }
    
    public OperatorNotFoundException(String msg) {
        super(msg);
    }
    
    public OperatorNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public OperatorNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public OperatorNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }
    
    

}
