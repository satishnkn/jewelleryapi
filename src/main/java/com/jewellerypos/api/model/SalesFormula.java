package com.jewellerypos.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "FORMULA")
public class SalesFormula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private long id;
	
    @Column(name= "FORMULA_CALC")
	private String formulaCalc;
	
    @Column(name= "WASTAGE")
	private String wastage;
	
    @Column(name= "MAKINGCHARGE")
	private String makingCharge;
	
    @Column(name= "TAX")
	private String tax;

}
