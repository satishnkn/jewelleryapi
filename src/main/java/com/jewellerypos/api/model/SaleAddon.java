package com.jewellerypos.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "SALE_ADDON")
public class SaleAddon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7712976169786270285L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAG_ADDON_ID")
	private long saleAddonId;
	
	@Column(name = "ADDON_PRODUCT_CODE")
	private long addonProductCode;
	
	@Column(name = "ADDON_PIECE")
	private long addonPiece;
	
	@Column(name = "ADDON_GROSS_WEIGHT")
	private double addonGrossWeight;
	
	@Column(name = "ADDON_NET_WEIGHT")
	private double addonNetWeight;
	
	@Column(name = "ADDON_LESS_WEIGHT")
	private double addonLessWeight;
	
	@Column(name = "ADDON_WASTAGE")
	private double addonWastage;
	
	@Column(name = "ADDON_MAKING_CHARGE")
	private double addonMakingCharge;
	
	@Column(name = "ADDON_RATE")
	private double addonrate;
	
	@Column(name = "ADDON_AMOUNT")
	private double addonAmount;

}
