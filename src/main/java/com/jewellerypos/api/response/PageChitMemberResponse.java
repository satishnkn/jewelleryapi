package com.jewellerypos.api.response;

import java.util.List;

import com.jewellerypos.api.model.ChitMember;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageChitMemberResponse {
    
    private List<ChitMember> chitMemberList;
    private int number;
    private int numberOfElements;
    private int size;
    private long totalElements;
    private int totalPages;

}
