package com.jewellerypos.api.response;

import java.time.LocalDateTime;
import java.util.List;

import com.jewellerypos.api.model.Purchase;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseResponse {
	
	private List<Purchase> purchaseList;
	private long purchaseBillno;
	private LocalDateTime purchaseBillDate;
	
	

}
