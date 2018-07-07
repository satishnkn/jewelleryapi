package com.jewellerypos.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CUSTOMER")
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUST_ID")
	private long custId;
	
	@Column(name = "CUST_NAME")
	private String custName;
	
	@Column(name = "CUST_MOBILE")
	private String custMobile;
	
	@Column(name = "CUST_ADDR1")
	private String custAddr1;
	
	@Column(name = "CUST_ADDR2")
	private String custAddr2;
	
	@Column(name = "CUST_PINCODE")
	private String custPincode;
	
	@Column(name = "CUST_EMAIL")
	private String custEmail;
	
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)  
    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;
	
	
	
	
	
	
	
	



}
