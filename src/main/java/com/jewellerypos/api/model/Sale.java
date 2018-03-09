package com.jewellerypos.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "SALE")
public class Sale implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3688592421435310471L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SALE_NO")
	private long saleNo;
	
	@NotNull
	@Column(name = "SALE_BILL_NO")
	private long saleBillNo;
	
	@NotNull
	@Column(name = "TAG_ID")
	private String tagId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "SALE_DATE")
	private LocalDateTime saleDate;
	
	
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
	private double saleTaxPercent;
	
	@Column(name = "DISCOUNT")
	private double discount;
	
	@Column(name = "ROUND_OF_AMOUNT")
	private double roundOfAmount;
	
	@Column(name = "OTHER_CHARGE")
	private double otherCharge;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "tag")
	private Set<SaleAddon> saleAddon;
	
	@Column(name = "AMOUNT")
	private double amount;
	
	@Column(name = "SALE_TYPE")
	private String saleType;
	
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

}
