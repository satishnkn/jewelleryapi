package com.jewellerypos.api.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.jewellerypos.api.model.SaleAddon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleListRequest {
	
	private String tagId;
	
	@NotNull
	private long productCode;
	
	private int piece;
	
	private double grossWeight;
	
	private double netWeight;
	
	private double lessWeight;
	
	private double rate;
	
	private double wastage;
	
	private double makingCharge;
	
	private String grossOrNet;
	
	private double saleTaxPercent;
	
	private double discount;
	
	private double roundOfAmount;
	
	private double otherCharge;
	
	private double amount;
	 
	private String saleType;
	
	private Set<SaleAddon> saleAddon;

}
