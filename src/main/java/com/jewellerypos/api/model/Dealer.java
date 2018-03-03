package com.jewellerypos.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "DEALER")
public class Dealer implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEALER_ID")
	private long dealerId;
	
	@Column(name = "DEALER_NAME")
    @NotNull
	private String dealerName;
	
	@Column(name = "DEALER_COMPANY")
    @NotNull
	private String dealerCompany;
	
	@Column(name = "DEALER_TIN_NO")
	private String dealerTinNo;
	
	@Column(name = "DEALER_GST_NO")
	private String dealerGstNo;
	
	@Column(name = "DEALER_REGISTRATION_NO")
	private String dealerRegistrationNo;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@NotNull
	@Column( name = "MOBILE_NO")
	private String mobileNo;
	
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
