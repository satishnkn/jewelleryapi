package com.jewellerypos.api.request;

import java.util.Set;

import com.jewellerypos.api.model.PurchaseAddon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseListRequest {
	
	private long productCode;
	
	private int piece;
	
	private double grossWeight;
	
	private double netWeight;
	
	private double lessWeight;
	
	private double rate;
	
	private double wastage;
	
	private double makingCharge;
	
	private String grossOrNet;
	
	private double purchaseTaxPercent;
	
	private double discount;
	
	private double roundOfAmount;
	
	private double otherCharge;
	
	private double amount;
	 
	private String purchaseType;
	
	private Set<PurchaseAddon> purchaseAddon;

}
