package com.jewellerypos.api.service;

import com.jewellerypos.api.model.ChitMember;
import com.jewellerypos.api.response.PageChitMemberResponse;

public interface ChitMemberService {

    public ChitMember createMember(ChitMember chitMember);

    public ChitMember updateMember(long chitmemberId, ChitMember chitMember);

    public PageChitMemberResponse getAllChitMember(int page, int size);

    public ChitMember getChitMemberById(long chitmemberId);

    
    
}
