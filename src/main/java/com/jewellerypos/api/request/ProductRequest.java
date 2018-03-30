package com.jewellerypos.api.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductRequest {
    
    @NotNull    
    private String productName;
    
    @NotNull
    private String productShortName;
    
    @NotNull
    private String taggedYorN;
    
    @DecimalMax(value = "100.0", message = "The decimal value can not be more than 100.00 ")
    private double wastage;
    
    @NotNull
    private String weightOrRate;
    
    @NotNull
    private long metalId;
    
    @NotNull
    private long operatorCode;
    
    private String tagPrefix;
    
    private long tagSeqno;

}
