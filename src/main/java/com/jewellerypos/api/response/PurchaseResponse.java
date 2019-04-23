package com.jewellerypos.api.response;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jewellerypos.api.model.Purchase;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class PurchaseResponse {
	
	/*private List<Purchase> purchaseList;
	private long purchaseBillno;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime purchaseBillDate;
	*/
	
	private long purchaseNo;
	
	private long purchaseBillNo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime purchaseDate;
	
	private long dealerId;
	
	private long productCode;
	
	private int piece;
	
	private double grossWeight;
	
	private double netWeight;
	
	private double lessWeight;
	
	private double rate;
	
	private double wastage;
	
	private double makingCharge;
	
	private String grossOrNet;
	
	private double purchaseTaxPercent;
	
	private double discount;
	
	private double roundOfAmount;
	
	private double otherCharge;
	
	private double amount;
	
	private String purchaseType;
	
	/*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PURCHASE_NO", nullable = false)
	private Set<PurchaseAddon> purchaseAddon;*/
	
	private String billRefNo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime billRefDate;
	
	private String billStatus;
	
	private String description;
	
	private double totalDiscount;
	
	private double totalRoundOf;
	
	private double totalAmount;
	
    private long operatorCode;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdOn;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)  
    private LocalDateTime updatedOn;
	

}
