package com.jewellerypos.api.error;

import java.text.MessageFormat;

public enum ErrorScenario {
    
    METAL_NOT_FOUND(JPOSErrorConstant.METAL_NOT_FOUND_ERRORCODE,"The requested metal  not found.","The requested metal not found."),
    RATE_NOT_UPDATED(JPOSErrorConstant.RATE_NOT_UPDATED_ERRORCODE,"The current rate not updated.","The current rate not updated."),
    PRODUCT_ALREADY_EXIST(JPOSErrorConstant.PRODUCTNAME_ALREADY_EXIST_ERRORCODE,"Product {0} already exist.","Product {0} already exist."),
    PRODUCT_NOT_FOUND(JPOSErrorConstant.PRODUCT_NOT_FOUND_ERRORCODE,"ProductCode {0} not found.","ProductCode {0} not found."),
	DEALER_NOT_FOUND(JPOSErrorConstant.DEALER_NOT_FOUND_ERRORCODE,"DealerId {0} not found.","DealerId {0} not found."),
	BILLNO_NOT_VALID(JPOSErrorConstant.BILLNO_NOT_VALID_ERRORCODE,"{0} not valid.","{0} not valid."),
	BILL_ALREADY_CANCELED(JPOSErrorConstant.BILL_ALREADY_CANCELED_ERRORCODE,"{0} was already cancelled.","{0} was already cancelled.")
	;
    
    public final String code;
    public final String logMessage;
    public final String responseMessage;
    
    private ErrorScenario(String code, String logMessage, String responseMessage) {
        this.code = code;
        this.logMessage = logMessage;
        this.responseMessage = responseMessage;
    }

    public String getCode() { return this.code; }

    public String getLogMessage(Object... args) {
        return MessageFormat.format(logMessage, args);
    }

    public String getLogMessage() {
        return this.logMessage;
    }

    public String getResponseMessage(Object... args) {
        return MessageFormat.format(this.responseMessage, args);
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public static ErrorScenario fromValue(String code) {
        ErrorScenario[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ErrorScenario errorScenario = var1[var3];
            if (errorScenario.code.equals(code)) {
                return errorScenario;
            }
        }

        throw new IllegalArgumentException(code);
    }


}
