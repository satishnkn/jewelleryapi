package com.jewellerypos.api.service;

import com.jewellerypos.api.model.Operator;
import com.jewellerypos.api.request.LoginRequest;
import com.jewellerypos.api.request.PasswordChangeRequest;
import com.jewellerypos.api.response.AuthStatusResponse;
import com.jewellerypos.api.response.PageOperatorResponse;

public interface OperatorService {

    public Operator createOperator(Operator operator);

    public Operator updateOperator(long operatorCode, Operator operator);

    public Operator getOperatorByCode(long productCode);

    public PageOperatorResponse getAllOperator(int page, int size);

    public AuthStatusResponse login(LoginRequest loginReq);

    public AuthStatusResponse passwordChange(PasswordChangeRequest req);

}
