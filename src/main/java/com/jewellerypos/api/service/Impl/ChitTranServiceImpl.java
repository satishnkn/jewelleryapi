package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.ChitMemberNotFoundException;
import com.jewellerypos.api.error.ChitTransactionNotFoundException;
import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.model.ChitMember;
import com.jewellerypos.api.model.ChitTran;
import com.jewellerypos.api.repository.ChitMemberRepository;
import com.jewellerypos.api.repository.ChitTranRepository;
import com.jewellerypos.api.service.ChitTranService;

@Service
@Transactional
public class ChitTranServiceImpl implements ChitTranService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChitTranServiceImpl.class);
    
    private  final ChitTranRepository  chitTranRepository;
    private final ChitMemberRepository chitMemberRepository;
    
    @Autowired
    public ChitTranServiceImpl(ChitTranRepository  chitTranRepository,ChitMemberRepository chitMemberRepository) {
        this.chitTranRepository = chitTranRepository;
        this.chitMemberRepository = chitMemberRepository;
    }

    @Override
    public ChitTran createTransaction(ChitTran chitTran) {
        chitTran.setUpdatedOn(LocalDateTime.now());
        ChitTran ct = chitTranRepository.save(chitTran);
        return ct;
    }

    @Override
    public ChitTran updateTransaction(long chittranId, ChitTran chitTran) {
        ChitTran exist = chitTranRepository.findByTranNo(chittranId);
        if(exist == null)
            throw new ChitTransactionNotFoundException(ErrorScenario.CHIT_TRANSACTION_NOT_FOUND, String.valueOf(chittranId));
        exist.setTranDate(chitTran.getTranDate());
        exist.setChitMemberSno(chitTran.getChitMemberSno());
        exist.setGroupCode(chitTran.getGroupCode());
        exist.setMsno(chitTran.getMsno());
        exist.setAmount(chitTran.getAmount());
        exist.setPaymentType(chitTran.getPaymentType());
        exist.setRemarks(chitTran.getRemarks());
        exist.setTranstatus(chitTran.getTranstatus());
        exist.setRate(chitTran.getRate());
        exist.setWeight(chitTran.getWeight());
        exist.setOperatorCode(chitTran.getOperatorCode());
        exist.setUpdatedOn(LocalDateTime.now());
        return null;
    }

    @Override
    public List<ChitTran> getTransactionByMember(long chitMemberSno) {
        ChitMember cm = chitMemberRepository.findByChitMemberSno(chitMemberSno);
        if(cm == null)
            throw new ChitMemberNotFoundException(ErrorScenario.CHIT_MEMBER_NOT_FOUND, String.valueOf(chitMemberSno));
        List<ChitTran> chitTranList = chitTranRepository.findByChitMemberSno(chitMemberSno);
        return chitTranList;
    }

    @Override
    public List<ChitTran> getAllTransaction(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size,
                new Sort(Sort.Direction.ASC, "tranDate"));
        Page<ChitTran> chittran =  chitTranRepository.findAll(pageRequest);
        return chittran.getContent();
    }

    @Override
    public ChitTran cancelTransaction(long tranNo) {
        ChitTran exist = chitTranRepository.findByTranNo(tranNo);
        if(exist == null)
            throw new ChitTransactionNotFoundException(ErrorScenario.CHIT_TRANSACTION_NOT_FOUND, String.valueOf(tranNo));
        exist.setTranstatus("CANCEL");
        ChitTran ct = chitTranRepository.save(exist);
        return ct;
    }

    @Override
    public ChitTran getTransactionByTranNo(long tranNo) {
        ChitTran ct = chitTranRepository.findByTranNo(tranNo);
        if(ct == null)
            throw new ChitTransactionNotFoundException(ErrorScenario.CHIT_TRANSACTION_NOT_FOUND, String.valueOf(tranNo));
        return ct;
    }

}
