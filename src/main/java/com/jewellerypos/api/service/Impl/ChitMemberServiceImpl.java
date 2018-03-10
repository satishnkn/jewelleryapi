package com.jewellerypos.api.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.ChitMemberNotFoundException;
import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.model.ChitMember;
import com.jewellerypos.api.repository.ChitMemberRepository;
import com.jewellerypos.api.response.PageChitMemberResponse;
import com.jewellerypos.api.service.ChitMemberService;

@Service
@Transactional
public class ChitMemberServiceImpl implements ChitMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChitMemberServiceImpl.class);
    
    private final ChitMemberRepository chitMemberRepository;
    public ChitMemberServiceImpl(ChitMemberRepository chitMemberRepository) {
        this.chitMemberRepository = chitMemberRepository;
    }

    @Override
    public ChitMember createMember(ChitMember chitMember) {
        ChitMember ch = chitMemberRepository.save(chitMember);
        return ch;
    }

    @Override
    public ChitMember updateMember(long chitmemberId, ChitMember chitMember) {
        ChitMember exist = chitMemberRepository.findByChitMemberSno(chitmemberId);
        if(exist != null)
            throw new ChitMemberNotFoundException(ErrorScenario.CHIT_MEMBER_NOT_FOUND, String.valueOf(chitmemberId));
        
        exist.setChitGroupSno(chitMember.getChitGroupSno());
        exist.setGroupCode(chitMember.getGroupCode());
        exist.setMsno(chitMember.getMsno());
        exist.setJoinDate(chitMember.getJoinDate());
        exist.setCustName(chitMember.getCustName());
        exist.setAddr1(chitMember.getAddr1());
        exist.setAddr2(chitMember.getAddr2());
        exist.setCity(chitMember.getCity());
        exist.setState(chitMember.getState());
        exist.setMobileno(chitMember.getMobileno());
        exist.setPhone(chitMember.getPhone());
        exist.setPincode(chitMember.getPincode());
        exist.setImagepath(chitMember.getImagepath());
        exist.setInstalmentAmt(chitMember.getInstalmentAmt());
        return chitMemberRepository.save(exist);
    }

    @Override
    public PageChitMemberResponse getAllChitMember(int page, int size) {
        PageChitMemberResponse response = new PageChitMemberResponse();
        if(size == 0){
            List<ChitMember> chitMemberList = chitMemberRepository.findAll();
            response.setChitMemberList(chitMemberList);
            response.setNumber(0);
            response.setNumberOfElements(chitMemberList.size());
            response.setSize(0);
            response.setTotalElements(chitMemberList.size());
            response.setTotalPages(1);
        }
        else{
            PageRequest pageRequest = new PageRequest(page, size,
                    new Sort(Sort.Direction.ASC, "custName"));
            Page<ChitMember> chitmemberPage = chitMemberRepository.findAll(pageRequest);
            response.setChitMemberList(chitmemberPage.getContent());
            response.setNumber(chitmemberPage.getNumber());
            response.setNumberOfElements(chitmemberPage.getNumberOfElements());
            response.setSize(chitmemberPage.getSize());
            response.setTotalElements(chitmemberPage.getTotalElements());
            response.setTotalPages(chitmemberPage.getTotalPages());
        }
        return response;
    }

    @Override
    public ChitMember getChitMemberById(long chitmemberId) {
        ChitMember ch = chitMemberRepository.findByChitMemberSno(chitmemberId);
        if(ch == null)
            throw new ChitMemberNotFoundException(ErrorScenario.CHIT_MEMBER_NOT_FOUND, String.valueOf(chitmemberId));
        return ch;
    }

}
