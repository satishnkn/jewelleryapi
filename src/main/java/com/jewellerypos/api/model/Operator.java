package com.jewellerypos.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@Table(name = "OPERATOR")
public class Operator implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6963894718047218501L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPERATOR_CODE")
    private long operatorCode;
    
    @Column(name = "OPERATOR_NAME")
    private String operatorName;
    
    @Column(name = "LOGIN_ID")
    private String loginId;
    
    @Column(name = "LOGIN_PASSWORD")
    private String loginPassword;
    
    @Column(name = "OPERATOR_QUALIFICATION")
    private String operatorQualification;
    
    @Column(name = "OPERATOR_DESIGNATION")
    private String operatorDesignation;
    
    @Column(name = "OPERATOR_ROLE")
    private String operatorRole;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "OPERATOR_CODE", nullable = false)
    private List<OperatorDevice> operatorDevice;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "OPERATOR_DOB")
    private LocalDateTime operatorDob;
    
    @Column(name = "CREATED_BY")
    private long createdBy;
    
    @Column(name = "UPDATED_BY")
    private long updatedBy;
    
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
