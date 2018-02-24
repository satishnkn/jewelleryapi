package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class ProductNotFoundException extends RuntimeException implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super();
    }
    
    public ProductNotFoundException(String msg) {
        super(msg);
    }
    
    public ProductNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public ProductNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public ProductNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }


}
