package com.jewellerypos.api.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "TAG_ADDON")
public class TagAddon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2396908143671675616L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAG_ADDON_ID")
	private long tagAddonId;
	
	
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
	

}
