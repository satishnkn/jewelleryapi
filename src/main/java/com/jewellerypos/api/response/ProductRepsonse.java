package com.jewellerypos.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRepsonse {
    
    private long productCode;
    
    private String productName;
    
    private String productShortName;
    
    private String taggedYorN;
    
    private double wastage;
    
    private String weightOrRate;
    
    private String metalName;
    

}
