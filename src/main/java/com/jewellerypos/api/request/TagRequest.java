package com.jewellerypos.api.request;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.jewellerypos.api.model.TagAddon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagRequest {
			
	@NotNull
	private long productCode;
	
	private int piece;
	
	private double grossWeight;
	
	private double netWeight;
	
	private double lessWeight;
	
	private double purchaseRate;
	
	private long purchaseNo;
	
	private double wastage;
	
	private double makingCharge;
	
	private String grossOrNet;
	
	private double discount;
		
	private double otherCharge;
	
	private Set<TagAddon> tagAddon;
	
    private long operatorCode;   
    

}
