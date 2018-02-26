package com.jewellerypos.api.request;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PurchaseRequest {
	
	
	private long dealerId;
	
	private List<PurchaseListRequest> purchaseList;
	
	private String description;
	
	private String billRefNo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime billRefDate;
	
	private String billStatus;
	
	private double totalDiscount;
	
	private double totalRoundOf;
	
	private double totalAmount;
	
    private long operatorCode;
    
   

}
