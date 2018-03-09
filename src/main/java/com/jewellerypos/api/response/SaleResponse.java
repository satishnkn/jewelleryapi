package com.jewellerypos.api.response;

import java.time.LocalDateTime;
import java.util.List;

import com.jewellerypos.api.model.Sale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleResponse {
	
	private List<Sale> saleList;
	private long saleBillno;
	private LocalDateTime saleBillDate;

}
