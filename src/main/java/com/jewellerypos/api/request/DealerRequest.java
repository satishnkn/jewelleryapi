package com.jewellerypos.api.request;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealerRequest {
	@NotNull
	private String dealerName;
	private String dealerCompany;
	private String dealerTinNo;
	private String dealerGstNo;
	private String dealerRegistrationNo;
	private String address;
	@NotNull
	private String mobileNo;
    private long operatorCode;

}
