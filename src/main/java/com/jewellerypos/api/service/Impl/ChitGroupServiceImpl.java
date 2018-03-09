package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.ChitGroupIdNotFoundException;
import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.model.ChitGroup;
import com.jewellerypos.api.repository.ChitGroupRepository;
import com.jewellerypos.api.service.ChitGroupService;

@Service
@Transactional
public class ChitGroupServiceImpl implements ChitGroupService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ChitGroupServiceImpl.class);
    
    private final ChitGroupRepository chitGroupRepository;
    
    @Autowired
    public ChitGroupServiceImpl(ChitGroupRepository chitGroupRepository) {
        this.chitGroupRepository = chitGroupRepository;
    }
    
    

    @Override
    public ChitGroup createChitGroup(ChitGroup chitGroup) {
        ChitGroup cg = chitGroupRepository.save(chitGroup);
        cg.setUpdatedOn(LocalDateTime.now());
        return cg;
    }

    @Override
    public ChitGroup updateChitGroup(long chitgroupId, ChitGroup chitGroup) {
        ChitGroup exist = chitGroupRepository.findByChitGroupSno(chitgroupId);
        if(exist == null)
            throw new ChitGroupIdNotFoundException(ErrorScenario.CHIT_GROUP_ID_NOT_FOUND, String.valueOf(chitgroupId));
        exist.setChitGroupSno(chitgroupId);
        exist.setChitGroupName(chitGroup.getChitGroupName());
        exist.setChitAmount(chitGroup.getChitAmount());
        exist.setChitGroupStatus(chitGroup.getChitGroupStatus());
        exist.setNoOfInstalment(chitGroup.getNoOfInstalment());
        exist.setOperatorCode(chitGroup.getOperatorCode());
        exist.setUpdatedOn(LocalDateTime.now());
        
        ChitGroup cg = chitGroupRepository.save(exist);
        return cg;
    }

    @Override
    public List<ChitGroup> getAllChitGroup(String status) {
        List<ChitGroup> response = new ArrayList<>();
        if(status != null)
            response = chitGroupRepository.findByChitGroupStatus(status);
        else
            response = chitGroupRepository.findAll();
        return response;
    }

    @Override
    public ChitGroup getChitGroupByChitGroupId(long chitgroupId) {
        ChitGroup exist = chitGroupRepository.findByChitGroupSno(chitgroupId);
        if(exist == null)
            throw new ChitGroupIdNotFoundException(ErrorScenario.CHIT_GROUP_ID_NOT_FOUND, String.valueOf(chitgroupId));
        return exist;
    }

}
