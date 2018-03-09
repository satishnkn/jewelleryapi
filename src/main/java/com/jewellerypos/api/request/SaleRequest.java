package com.jewellerypos.api.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleRequest {
	
	private List<SaleListRequest> salesList;
	
	private String description;
	
	private String billStatus;
	
	private double totalDiscount;
	
	private double totalRoundOf;
	
	private double totalAmount;
	
    private long operatorCode;
	

}
