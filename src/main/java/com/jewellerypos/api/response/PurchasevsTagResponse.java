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
    
    private long purchaseNo;
    
    private long purchaseBillNo;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime purchaseDate;
    
    private long dealerId;
    
    private long productCode;
    
    private long piece;
    
    private double grossWt;
    
    private double netWt;
    
    private double lessWt;
    
    private double otherCharge;
    
    private double billrefNo;
    
    private double tproductCode;
    
    private double tgrossWt;
    
    private double tnetWt;
    
    private double tlessWt;
    
    private long tpurchaseNo;
}
