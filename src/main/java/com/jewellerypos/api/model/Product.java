package com.jewellerypos.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

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
@Table(name = "PRODUCT")
public class Product implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -6570555011511700295L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_CODE")
    private long productCode;
    
    @Column(name = "PRODUCT_NAME")
    @NotNull
    private String productName;
    
    @Column(name = "PRODUCT_SHORT_NAME")
    @NotNull
    private String productShortName;
    
    @Column(name = "TAGGEDYORN")
    @NotNull
    private String taggedYorN;
    
    @Column(name = "WASTAGE")
    @DecimalMax(value = "100.0", message = "The decimal value can not be more than 100.00 ")
    private double wastage;
    
    @Column(name = "WEIGHT_OR_RATE")
    @NotNull
    private String weightOrRate;
    
    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "METAL_CODE" ,nullable = false)
    private Metal metal;
    
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
