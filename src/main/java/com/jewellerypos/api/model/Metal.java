package com.jewellerypos.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "METAL")
public class Metal implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3971143961520685885L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "METAL_ID")
    private long metalId;
    
    @Column(name= "METAL_NAME")
    private String metalName;
    
    @Column(name="METAL_RATE")
    private double metalRate;
    
    @Column(name = "METAL_PURITY")
    private double metalPurity;
    
    @Column(name = "METAL_TYPE")
    private String metalType;
    

}
