package com.jewellerypos.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesFormulaRequest {
		
	private String formulaCalc;
	
	private String wastage;
	
	private String makingCharge;
	
	private String tax;


}
