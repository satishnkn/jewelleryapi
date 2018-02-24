package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class MetalNotFoundException extends RuntimeException implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MetalNotFoundException() {
        super();
    }
    
    public MetalNotFoundException(String msg) {
        super(msg);
    }
    
    public MetalNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public MetalNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public MetalNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }

}
