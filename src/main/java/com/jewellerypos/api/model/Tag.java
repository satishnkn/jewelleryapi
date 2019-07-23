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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
@Table(name = "TAG")
public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8288702134276276640L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAG_NO")
	private long tagNo;
	
	
	@Column(name = "TAG_ID")
	private String tagId;
			
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "TAG_PLUS_DATE")
	private LocalDateTime tagPlusDate;
		
	@NotNull
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
	
	@Column(name = "PURCHASE_RATE")
	private double purchaseRate;
	
	@Column(name = "PURCHASE_NO")
	private long purchaseNo;
	
	@Column(name = "WASTAGE")
	private double wastage;
	
	@Column(name = "WASTAGE_WEIGHT")
	private double wastageWeight;
	
	@Column(name = "MAKING_CHARGE")
	private double makingCharge;
	
	@Column(name = "GROSS_OR_NET")
	private String grossOrNet;
	
	@Column(name = "DISCOUNT")
	private double discount;
		
	@Column(name = "OTHER_CHARGE")
	private double otherCharge;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "TAG_NO", nullable = false)
	private Set<TagAddon> tagAddon;
	
	@Column(name = "SALE_BILL_NO")
	private String saleBillNo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "TAG_MINUS_DATE")
	private LocalDateTime tagMinusDate;
		
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
