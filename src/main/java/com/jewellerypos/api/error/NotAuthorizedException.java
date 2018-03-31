package com.jewellerypos.api.error;

public class NotAuthorizedException extends RuntimeException{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NotAuthorizedException() {
        super();
    }
    

    public NotAuthorizedException(String msg) {
        super(msg);
    }
    
    public NotAuthorizedException(String msg,Exception exp) {
        super(msg,exp);
    }
    
    public NotAuthorizedException(ErrorScenario errorScenario) {
        super(errorScenario.code+"-"+errorScenario.responseMessage);
    }

}
