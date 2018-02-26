package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.DealerNotFoundException;
import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.model.Dealer;
import com.jewellerypos.api.repository.DealerRepository;
import com.jewellerypos.api.request.DealerRequest;
import com.jewellerypos.api.response.PageDealerResponse;
import com.jewellerypos.api.restcontroller.DealerController;
import com.jewellerypos.api.service.DealerService;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DealerServiceImpl.class); 
	
	private final DealerRepository dealerRepository;
	
	public DealerServiceImpl(DealerRepository dealerRepository) {
		this.dealerRepository = dealerRepository;
	}

	@Override
	public Dealer createDealer(DealerRequest dealerReq) {
		Dealer crDealer = new Dealer();
		Dealer dealer = new Dealer();
		dealer.setDealerName(dealerReq.getDealerName());
		dealer.setDealerCompany(dealerReq.getDealerCompany());
		dealer.setDealerTinNo(dealerReq.getDealerTinNo());
		dealer.setDealerGstNo(dealerReq.getDealerGstNo());
		dealer.setDealerRegistrationNo(dealerReq.getDealerRegistrationNo());
		dealer.setOperatorCode(dealerReq.getOperatorCode());
		dealer.setCreatedOn(LocalDateTime.now());
		dealer.setUpdatedOn(LocalDateTime.now());
		
		crDealer = dealerRepository.save(dealer);
		
		return crDealer;
	}

	@Override
	public Dealer updateDealer(long dealerId, DealerRequest dealerReq) {
		Dealer updated = null ;
		Dealer exist = dealerRepository.findByDealerId(dealerId);
		if(exist == null)
			 throw new DealerNotFoundException(ErrorScenario.DEALER_NOT_FOUND, String.valueOf(dealerId));
		exist.setDealerName(dealerReq.getDealerName());
		exist.setDealerCompany(dealerReq.getDealerCompany());
		exist.setDealerTinNo(dealerReq.getDealerTinNo());
		exist.setDealerGstNo(dealerReq.getDealerGstNo());
		exist.setDealerRegistrationNo(dealerReq.getDealerRegistrationNo());
		exist.setOperatorCode(dealerReq.getOperatorCode());
		exist.setUpdatedOn(LocalDateTime.now());
		updated = dealerRepository.save(exist);
		return updated;
	}

	@Override
	public PageDealerResponse getAllDealer(int page, int size) {
		PageDealerResponse response = new PageDealerResponse();
		if (size == 0) {
			List<Dealer> dealerList = dealerRepository.findAll();
			response.setDealerList(dealerList);
			response.setNumber(0);
            response.setNumberOfElements(dealerList.size());
            response.setSize(0);
            response.setTotalElements(dealerList.size());
            response.setTotalPages(1);
		}
		else {
			 PageRequest pageRequest = new PageRequest(page, size,
		                new Sort(Sort.Direction.ASC, "dealerName"));
			 Page<Dealer> pageDealer = dealerRepository.findAll(pageRequest);
			 	response.setDealerList(pageDealer.getContent()); 
	            response.setNumber(pageDealer.getNumber());
	            response.setNumberOfElements(pageDealer.getNumberOfElements());
	            response.setTotalElements(pageDealer.getTotalElements());
	            response.setTotalPages(pageDealer.getTotalPages());
	            response.setSize(pageDealer.getSize()); 
			
		}
		
		
		return response;
	}

	@Override
	public Dealer getDealerById(long dealerId) {
		Dealer dealer = dealerRepository.findByDealerId(dealerId);
		if(dealer == null)
			throw new DealerNotFoundException(ErrorScenario.DEALER_NOT_FOUND, String.valueOf(dealerId));
	
		return dealer;
	}
	
	

}
