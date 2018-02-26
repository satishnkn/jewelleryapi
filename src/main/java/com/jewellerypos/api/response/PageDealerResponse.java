package com.jewellerypos.api.response;

import java.util.List;

import com.jewellerypos.api.model.Dealer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDealerResponse {
	
	private List<Dealer> dealerList;
	private int number;
	private int numberOfElements;
	private int size;
	private long totalElements;
	private int totalPages;
	
	

}
