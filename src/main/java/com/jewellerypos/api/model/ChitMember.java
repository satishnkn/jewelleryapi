package com.jewellerypos.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "CHITMEMBER")
public class ChitMember {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHIT_MEMBER_SNO")
    private long chitMemberSno;
    
    @Column(name = "CHIT_GROUP_SNO")
    private long chitGroupSno;
    
    @Column(name = "GROUP_CODE")
    private String groupCode;
    
    @Column(name = "MEMBER_SNO")
    private long memberSno;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)  
    @Column(name = "JOIN_DATE")
    private LocalDateTime joinDate; 
    
    @Column(name = "CUST_NAME")
    private String custName;
    
    @Column(name = "ADDR1")
    private String addr1;
    
    @Column(name = "ADDR2")
    private String addr2;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name= "STATE")
    private String state;
    
    @Column(name = "MOBILENO")
    private String mobileno;
    
    @Column(name = "PHONE")
    private String phone;
    
    @Column(name = "PINCODE")
    private String pincode;
    
    @Column(name = "IMAGERPATH")
    private String imagepath;
    
    @Column(name = "INSTALMENT_AMT")
    private double instalmentAmt;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)  
    @Column(name = "CLOSE_DATE")
    private LocalDateTime closeDate;
    
    @Column(name = "SALE_BILLREFNO")
    private long saleBillrefno;
    

}
