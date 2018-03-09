package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class ChitGroupIdNotFoundException extends RuntimeException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ChitGroupIdNotFoundException() {
        super();
    }
    
    public ChitGroupIdNotFoundException(String msg) {
        super(msg);
    }
    
    public ChitGroupIdNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public ChitGroupIdNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public ChitGroupIdNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }

}
