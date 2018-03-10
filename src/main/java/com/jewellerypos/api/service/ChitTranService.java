package com.jewellerypos.api.service;

import java.util.List;

import com.jewellerypos.api.model.ChitTran;

public interface ChitTranService {

    public ChitTran createTransaction(ChitTran chitTran);

    public ChitTran updateTransaction(long chittranId, ChitTran chitTran);

    public List<ChitTran> getTransactionByMember(long chitMemberSno);

    public List<ChitTran> getAllTransaction(int page, int size);

    public ChitTran cancelTransaction(long tranNo);

    public ChitTran getTransactionByTranNo(long tranNo);

}
