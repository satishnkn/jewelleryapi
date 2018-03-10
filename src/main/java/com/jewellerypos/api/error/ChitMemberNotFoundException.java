package com.jewellerypos.api.error;

import java.io.Serializable;
import java.text.MessageFormat;

public class ChitMemberNotFoundException extends RuntimeException implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ChitMemberNotFoundException() {
        super();
    }
    
    public ChitMemberNotFoundException(String msg) {
        super(msg);
    }
    
    public ChitMemberNotFoundException(String msg,Exception exp) {
        super(msg,exp);
    }
    public ChitMemberNotFoundException(ErrorScenario errorScenario,String param)
    {
        super(new MessageFormat(errorScenario.responseMessage).format(new Object[]{param}));        
    }
    
    public ChitMemberNotFoundException(ErrorScenario errorScenario)
    {
        super(errorScenario.responseMessage);        
    }

}
