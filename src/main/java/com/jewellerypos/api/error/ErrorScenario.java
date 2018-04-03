package com.jewellerypos.api.error;

import java.text.MessageFormat;

public enum ErrorScenario {
    
    METAL_NOT_FOUND(JPOSErrorConstant.METAL_NOT_FOUND_ERRORCODE,"The requested metal  not found.","The requested metal not found."),
    RATE_NOT_UPDATED(JPOSErrorConstant.RATE_NOT_UPDATED_ERRORCODE,"The current rate not updated.","The current rate not updated."),
    PRODUCT_ALREADY_EXIST(JPOSErrorConstant.PRODUCTNAME_ALREADY_EXIST_ERRORCODE,"Product {0} already exist.","Product {0} already exist."),
    PRODUCT_NOT_FOUND(JPOSErrorConstant.PRODUCT_NOT_FOUND_ERRORCODE,"ProductCode {0} not found.","ProductCode {0} not found."),
	DEALER_NOT_FOUND(JPOSErrorConstant.DEALER_NOT_FOUND_ERRORCODE,"DealerId {0} not found.","DealerId {0} not found."),
	DEALER_ALREADY_EXIST(JPOSErrorConstant.DEALER_ALREADY_EXIST_ERRORCODE,"Dealer with mobileno {0} already exist.","Dealer with mobileno {0} already exist."),
	BILLNO_NOT_VALID(JPOSErrorConstant.BILLNO_NOT_VALID_ERRORCODE,"{0} not valid.","{0} not valid."),
	BILL_ALREADY_CANCELED(JPOSErrorConstant.BILL_ALREADY_CANCELED_ERRORCODE,"{0} was already cancelled.","{0} was already cancelled."),
	TAG_NOT_FOUND(JPOSErrorConstant.TAG_NOT_FOUND_ERRORCODE,"{0} is not found.","{0} is not found."),
	CHIT_GROUP_ID_NOT_FOUND(JPOSErrorConstant.CHIT_GROUP_ID_NOT_FOUND_ERRORCODE,"Chitgroup id {0} is not found.","Chitgroup id {0} is not found."),
	CHIT_MEMBER_NOT_FOUND(JPOSErrorConstant.CHIT_MEMBER_NOT_FOUND_ERRORCODE,"Chit Member id {0} is not found.","Chit Member id {0} is not found."),
	CHIT_TRANSACTION_NOT_FOUND(JPOSErrorConstant.CHIT_TRANSACTION_NOT_FOUND_ERRORCODE,"Chit Transaction {0} is not found.","Chit Transaction {0} is not found."),
	MOBILENO_ALREADY_EXIST(JPOSErrorConstant.MOBILENO_ALREADY_EXIST_ERRORCODE, "Mobile No {0} Already Exist.", "Mobile No {0} Already Exist."),
	OPERATOR_NOT_FOUND(JPOSErrorConstant.OPERATOR_NOT_FOUND_ERRORCODE, "Operator not Found.","Operator not Found."),
	PASSWORD_NOT_MATCHED(JPOSErrorConstant.PASSWORD_NOT_MATCHED_ERRORCODE,"Password not Matched.-"+JPOSErrorConstant.PASSWORD_NOT_MATCHED_ERRORCODE,"Password not Matched.-"+JPOSErrorConstant.PASSWORD_NOT_MATCHED_ERRORCODE),
	COOKIE_NOT_MATCHED(JPOSErrorConstant.COOKIE_NOT_MATCHED_ERRORCODE,"Cookie not matched","Cookie not matched"),
	OPERATION_FORBIDDEN(JPOSErrorConstant.OPERATION_FORBIDDEN_ERRORCODE,"Operation forbidden for your role.","Operation forbidden for your role."),
	PURCHASE_NOT_FOUND(JPOSErrorConstant.PURCHASE_NOT_FOUND_ERRORCODE, "Purchase BillNo {0} is not found.","Purchase BillNo {0} is not found.")
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
