package com.jewellerypos.api.response;

import java.util.List;

import com.jewellerypos.api.model.Purchase;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagePurchaseResponse {
	
	private List<Purchase> purchaseLst;
	private int number;
	private int numberOfElements;
	private int size;
	private long totalElements;
	private int totalPages;

}
