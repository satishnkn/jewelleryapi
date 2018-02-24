package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.error.MetalNotFoundException;
import com.jewellerypos.api.error.RateNotUpdatedException;
import com.jewellerypos.api.model.Metal;
import com.jewellerypos.api.model.MetalRateHistory;
import com.jewellerypos.api.repository.MetalRateHistoryRepository;
import com.jewellerypos.api.repository.MetalRepository;
import com.jewellerypos.api.request.MetalRateHistoryRequest;
import com.jewellerypos.api.restcontroller.MetalRateHistoryController;
import com.jewellerypos.api.service.MetalRateHistoryService;


@Service
@Transactional
public class MetalRateHistoryServiceImpl implements MetalRateHistoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetalRateHistoryServiceImpl.class);
    
    private final MetalRateHistoryRepository metalRateHistoryRepository;
    private final MetalRepository metalRepository;
    
    @Autowired
    public MetalRateHistoryServiceImpl(MetalRateHistoryRepository metalRateHistoryRepository,MetalRepository metalRepository) {
        this.metalRateHistoryRepository = metalRateHistoryRepository;
        this.metalRepository = metalRepository;
    }
    

    @Override
    public MetalRateHistory createHistory(MetalRateHistoryRequest metalRateRequest) {
        Metal metal = metalRepository.findByMetalId(metalRateRequest.getMetalId());
        if(metal == null)
            throw new MetalNotFoundException(ErrorScenario.METAL_NOT_FOUND);
        MetalRateHistory metalrate = new MetalRateHistory();
        metalrate.setMetalId(metal.getMetalId());
        metalrate.setMetalName(metal.getMetalName());
        metalrate.setMetalPurity(metal.getMetalPurity());
        metalrate.setMetalType(metal.getMetalType());
        metalrate.setMetalRate(metalRateRequest.getMetalRate());
        metalrate.setOnDate(metalRateRequest.getOnDate());
        metalrate.setOperatorCode(metalRateRequest.getOperatorCode());
        metalrate.setUpdatedOn(LocalDateTime.now());
        
        MetalRateHistory updatedHistory = metalRateHistoryRepository.save(metalrate);
        metal.setMetalRate(metalRateRequest.getMetalRate());
        Metal updated =  metalRepository.save(metal);
        if(updated != null && updatedHistory != null)
            return updatedHistory;
        else
            throw new RateNotUpdatedException(ErrorScenario.RATE_NOT_UPDATED);
    }
    

}
