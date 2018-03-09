package com.jewellerypos.api.service;

import java.util.List;

import com.jewellerypos.api.model.ChitGroup;

public interface ChitGroupService {

    public ChitGroup createChitGroup(ChitGroup chitGroup);

    public ChitGroup updateChitGroup(long chitgroupId, ChitGroup chitGroup);

    public List<ChitGroup> getAllChitGroup(String status);

    public ChitGroup getChitGroupByChitGroupId(long chitgroupId);

}
