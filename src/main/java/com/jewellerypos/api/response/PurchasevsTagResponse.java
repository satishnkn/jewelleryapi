package com.jewellerypos.api.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchasevsTagResponse {
    
    private String purchaseNo;
    
    private String purchaseBillNo;
    
    private String purchaseDate;
    
    private String dealerId;
    
    private String productCode;
    
    private String piece;
    
    private String grossWt;
    
    private String netWt;
    
    private String lessWt;
    
    private String otherCharge;
    
    private String billrefNo;
    
    private String tproductCode;
    
    private String tgrossWt;
    
    private String tnetWt;
    
    private String tlessWt;
    
    private String tpurchaseNo;
}
