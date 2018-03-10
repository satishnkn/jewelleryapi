package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class ChitTransactionNotFoundException extends RuntimeException implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ChitTransactionNotFoundException() {
        super();
    }
    
    public ChitTransactionNotFoundException(String msg) {
        super(msg);
    }
    
    public ChitTransactionNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public ChitTransactionNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public ChitTransactionNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }
    
    

}
