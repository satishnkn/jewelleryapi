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
@Table(name = "CHITTRAN")
public class ChitTran {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAN_NO")
    private long tranNo;
    
    @Column(name = "TRAN_DATE")
    private LocalDateTime tranDate;
    
    @Column(name = "CHIT_MEMBER_SNO")
    private long chitMemberSno;
    
    @Column(name = "GROUP_CODE")
    private String groupCode;
    
    @Column(name = "MSNO")
    private long msno;
    
    @Column(name = "AMOUNT")
    private double amount;
    
    @Column(name = "PAYMENT_TYPE")
    private String paymentType;
    
    @Column(name = "REMARKS")
    private String remarks;
    
    @Column(name = "TRAN_STATUS")
    private String transtatus;
    
    @Column(name = "RATE")
    private double rate;
    
    @Column(name = "WEIGHT")
    private double weight;
    
    @Column(name = "OPERATOR_CODE")
    private long operatorCode;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)  
    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;

}

