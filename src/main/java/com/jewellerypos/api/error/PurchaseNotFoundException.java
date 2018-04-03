package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class PurchaseNotFoundException extends RuntimeException implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public PurchaseNotFoundException() {
        super();
    }
    
    public PurchaseNotFoundException(String msg) {
        super(msg);
    }
    
    public PurchaseNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public PurchaseNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public PurchaseNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }


}
