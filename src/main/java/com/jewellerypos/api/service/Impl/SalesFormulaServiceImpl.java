package com.jewellerypos.api.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.model.SalesFormula;
import com.jewellerypos.api.repository.SalesFormulaRepository;
import com.jewellerypos.api.request.SalesFormulaRequest;
import com.jewellerypos.api.service.SalesFormulaService;

@Service
public class SalesFormulaServiceImpl implements SalesFormulaService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesFormulaServiceImpl.class);

	private final SalesFormulaRepository salesFormulaRepository;
	
	@Autowired
	public SalesFormulaServiceImpl(SalesFormulaRepository salesFormulaRepository) {
		this.salesFormulaRepository = salesFormulaRepository;
	}
	
	
	@Override
	public SalesFormula createFormula(SalesFormulaRequest salesFormula) {
		SalesFormula req = new SalesFormula();
		req.setFormulaCalc(salesFormula.getFormulaCalc());
		req.setWastage(salesFormula.getWastage());
		req.setMakingCharge(salesFormula.getMakingCharge());
		req.setTax(salesFormula.getTax());
		SalesFormula sf = salesFormulaRepository.save(req );
		
		return sf;
	}


	@Override
	public List<SalesFormula> findAllFormula() {
		return salesFormulaRepository.findAll();
	}
	
	

}
