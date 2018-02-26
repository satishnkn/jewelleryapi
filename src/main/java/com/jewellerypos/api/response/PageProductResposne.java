package com.jewellerypos.api.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageProductResposne {
	
	private List<ProductRepsonse> productLst;
	private int number;
	private int numberOfElements;
	private int size;
	private long totalElements;
	private int totalPages;

}
