package com.jewellerypos.api.response;

import java.util.List;

import com.jewellerypos.api.model.Operator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageOperatorResponse {
    
    private List<Operator> operator;
    private int number;
    private int numberOfElements;
    private int size;
    private long totalElements;
    private int totalPages;

}
