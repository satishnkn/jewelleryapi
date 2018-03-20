package com.jewellerypos.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PURCHASE")
public class Purchase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6447583799955157583L;
	 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PURCHASE_NO")
	private long purchaseNo;
	
	@Column(name = "PURCHASE_BILL_NO")
	private long purchaseBillNo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "PURCHASA_DATE")
	private LocalDateTime purchaseDate;
	
	@Column(name = "DEALER_ID")
	private long dealerId;
	
	@Column(name = "PRODUCT_CODE")
	private long productCode;
	
	@Column(name = "PIECE")
	private int piece;
	
	@Column(name = "GROSS_WEIGHT")
	private double grossWeight;
	
	@Column(name = "NET_WEIGHT")
	private double netWeight;
	
	@Column(name = "LESS_WEIGHT")
	private double lessWeight;
	
	@Column(name = "RATE")
	private double rate;
	
	@Column(name = "WASTAGE")
	private double wastage;
	
	@Column(name = "MAKING_CHARGE")
	private double makingCharge;
	
	@Column(name = "GROSS_OR_NET")
	private String grossOrNet;
	
	@Column(name = "PURCHASE_TAX_PERCENT")
	private double purchaseTaxPercent;
	
	@Column(name = "DISCOUNT")
	private double discount;
	
	@Column(name = "ROUND_OF_AMOUNT")
	private double roundOfAmount;
	
	@Column(name = "OTHER_CHARGE")
	private double otherCharge;
	
	@Column(name = "AMOUNT")
	private double amount;
	
	@Column(name = "PURCHASE_TYPE")
	private String purchaseType;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PURCHASE_NO", nullable = false)
	private Set<PurchaseAddon> purchaseAddon;
	
	@Column(name = "BILL_REFNO")
	private String billRefNo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "BILL_REFDATE")
	private LocalDateTime billRefDate;
	
	@Column(name = "BILL_STATUS")
	private String billStatus;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "TOTAL_DISCOUNT")
	private double totalDiscount;
	
	@Column(name = "TOTAL_ROUND_OF")
	private double totalRoundOf;
	
	@Column(name = "TOTAL_AMOUNT")
	private double totalAmount;
	
	@Column(name = "OPERATOR_CODE")
    private long operatorCode;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)  
    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;
    
    
    /*public void addPurchaseAddon(PurchaseAddon paddon) {
        if (paddon != null) {
           if (purchaseAddon == null) {
               purchaseAddon = new HashSet<PurchaseAddon>();          
           }
           purchaseAddon.add(paddon);
           paddon.setPurchase(this);
        }
     }*/
	
}