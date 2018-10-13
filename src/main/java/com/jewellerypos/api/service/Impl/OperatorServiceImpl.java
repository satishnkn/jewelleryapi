package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.error.NotAuthorizedException;
import com.jewellerypos.api.error.OperatorAlreadyExistException;
import com.jewellerypos.api.error.OperatorNotFoundException;
import com.jewellerypos.api.model.Dealer;
import com.jewellerypos.api.model.Operator;
import com.jewellerypos.api.model.OperatorDevice;
import com.jewellerypos.api.repository.OperatorRepository;
import com.jewellerypos.api.request.LoginRequest;
import com.jewellerypos.api.request.PasswordChangeRequest;
import com.jewellerypos.api.response.AuthStatusResponse;
import com.jewellerypos.api.response.PageOperatorResponse;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.service.OperatorService;
import com.jewellerypos.api.util.PasscodeEncryptorUtil;

@Service
@Transactional
public class OperatorServiceImpl implements OperatorService{
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatorServiceImpl.class);
    
    private final OperatorRepository operatorRepository;
    
    @Autowired
    public OperatorServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    public Operator createOperator(Operator operator) {
        Operator response = new Operator();
        Operator existOpertaor = operatorRepository.findByLoginId(operator.getLoginId()); 
        if(existOpertaor != null)
            throw new OperatorAlreadyExistException(ErrorScenario.MOBILENO_ALREADY_EXIST,operator.getLoginId());
        operator.setCreatedOn(LocalDateTime.now());
        operator.setUpdatedOn(LocalDateTime.now());
        operator.setLoginPassword(PasscodeEncryptorUtil.encryptPasscode(operator.getLoginPassword()));
        operator.getOperatorDevice().get(0).setLastLoggedIn(LocalDateTime.now());
        operator.setCreatedBy(1);
        operator.setUpdatedBy(1);
        response = operatorRepository.save(operator);
        return response;
    }

    @Override
    public Operator updateOperator(long operatorCode, Operator operator) {
        Operator existOperator = operatorRepository.findByOperatorCode(operatorCode);
        if(existOperator == null)
            throw new OperatorNotFoundException(ErrorScenario.OPERATOR_NOT_FOUND);
        existOperator.setOperatorDesignation(operator.getOperatorDesignation());
        existOperator.setOperatorDob(operator.getOperatorDob());
        existOperator.setOperatorName(operator.getOperatorName());
        existOperator.setOperatorQualification(operator.getOperatorQualification());
        existOperator.setOperatorRole(operator.getOperatorRole());
        existOperator.setUpdatedOn(LocalDateTime.now());
        Operator response = operatorRepository.save(existOperator);
        return response;
    }

    @Override
    public Operator getOperatorByCode(long operatorCode) {
        Operator operator = operatorRepository.findByOperatorCode(operatorCode);
        if(operator == null)
            throw new OperatorNotFoundException(ErrorScenario.OPERATOR_NOT_FOUND);
        return operator;
    }

    @Override
    public PageOperatorResponse getAllOperator(int page, int size) {
        PageOperatorResponse response = new PageOperatorResponse();
        if(size == 0){
            List<Operator> operatorList = operatorRepository.findAll();
            response.setOperator(operatorList);
            response.setNumber(0);
            response.setNumberOfElements(operatorList.size());
            response.setSize(0);
            response.setTotalElements(operatorList.size());
            response.setTotalPages(1);
        }
        else{
            PageRequest pageRequest = new PageRequest(page, size,
                    new Sort(Sort.Direction.ASC, "operatorName"));
         Page<Operator> pageOperator = operatorRepository.findAll(pageRequest);
            response.setOperator(pageOperator.getContent());
            response.setNumber(pageOperator.getNumber());
            response.setNumberOfElements(pageOperator.getNumberOfElements());
            response.setTotalElements(pageOperator.getTotalElements());
            response.setTotalPages(pageOperator.getTotalPages());
            response.setSize(pageOperator.getSize()); 
        }
        
        return response;
    }

    @Override
    public AuthStatusResponse login(LoginRequest loginReq) {
        AuthStatusResponse response = new AuthStatusResponse();
        Operator op = operatorRepository.findByLoginId(loginReq.getLoginId());
        if(op == null)
            throw new OperatorNotFoundException(ErrorScenario.OPERATOR_NOT_FOUND);
        String pwd = PasscodeEncryptorUtil.encryptPasscode(loginReq.getLoginPwd());
        System.out.println("test passwordString:::"+pwd);
        List<OperatorDevice> filterList = null;
        if(pwd.equals(op.getLoginPassword())){
            UUID cookie = UUID.randomUUID();
            OperatorDevice opDevice = op.getOperatorDevice().stream().filter(p -> p.getDeviceId().equals(loginReq.getLoginDeviceId())).filter(act -> act.getDeviceStatus().equals("ACTIVE")).findAny().orElse(null);
            if(opDevice != null){
                op.getOperatorDevice().remove(opDevice);
                filterList = op.getOperatorDevice();
                opDevice.setCookie(cookie.toString());
                opDevice.setLastLoggedIn(LocalDateTime.now());
                op.getOperatorDevice().add(opDevice);
            }
            else {
                OperatorDevice lastActive = op.getOperatorDevice().stream().filter(act -> act.getDeviceStatus().equals("ACTIVE")).findAny().orElse(null);
                if(lastActive != null)
                {
                    op.getOperatorDevice().remove(lastActive);
                    filterList = op.getOperatorDevice();
                    lastActive.setDeviceStatus("INACTIVE");
                    op.getOperatorDevice().add(lastActive);
                    
                }
                    OperatorDevice ndevice = new OperatorDevice();
                    ndevice.setDeviceId(loginReq.getLoginDeviceId());
                    ndevice.setCookie(cookie.toString());
                    ndevice.setDeviceStatus("ACTIVE");
                    ndevice.setLastLoggedIn(LocalDateTime.now());
                    op.getOperatorDevice().add(ndevice);
                }
              operatorRepository.save(op);
              response.setCookie(cookie.toString());
              response.setOperatorCode(op.getOperatorCode());
              response.setStatus(true);
            }
        else
            throw new NotAuthorizedException(ErrorScenario.PASSWORD_NOT_MATCHED);
        return response;
        }

    @Override
    public AuthStatusResponse passwordChange(PasswordChangeRequest req) {
        AuthStatusResponse response = new AuthStatusResponse();
        Operator op = operatorRepository.findByLoginId(req.getLoginId());
        if(op == null)
            throw new OperatorNotFoundException(ErrorScenario.OPERATOR_NOT_FOUND);
        String pwd = PasscodeEncryptorUtil.encryptPasscode(req.getOldPassword());
        if(pwd.equals(op.getLoginPassword())){
            op.setLoginPassword(pwd);
            operatorRepository.save(op);
            response.setCookie("Login with new Password");
            response.setStatus(true);
        }
        throw new NotAuthorizedException(ErrorScenario.PASSWORD_NOT_MATCHED);
    }

}
