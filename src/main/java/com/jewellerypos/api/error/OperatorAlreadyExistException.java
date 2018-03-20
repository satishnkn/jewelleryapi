package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class OperatorAlreadyExistException extends RuntimeException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public OperatorAlreadyExistException() {
        super();
    }
    
    public OperatorAlreadyExistException(String msg) {
        super(msg);
    }
    
    public OperatorAlreadyExistException(String msg,Exception exp) {
        super(msg,exp);
    }
    public OperatorAlreadyExistException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public OperatorAlreadyExistException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }
    

}
