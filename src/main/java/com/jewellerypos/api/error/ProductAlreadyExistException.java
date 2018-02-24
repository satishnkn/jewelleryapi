package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class ProductAlreadyExistException extends RuntimeException implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public ProductAlreadyExistException() {
        super();
    }
    
    public ProductAlreadyExistException(String msg) {
        super(msg);
    }
    
    public ProductAlreadyExistException(String msg,Exception exp) {
        super(msg,exp);
    }
    public ProductAlreadyExistException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public ProductAlreadyExistException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }


}
