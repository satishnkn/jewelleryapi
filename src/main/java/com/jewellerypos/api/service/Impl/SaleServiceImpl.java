package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.BillAlreadyCancelledException;
import com.jewellerypos.api.error.BillnoNotValidException;
import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.model.Control;
import com.jewellerypos.api.model.Purchase;
import com.jewellerypos.api.model.Sale;
import com.jewellerypos.api.repository.ControlRepository;
import com.jewellerypos.api.repository.ProductRepository;
import com.jewellerypos.api.repository.SaleRepository;
import com.jewellerypos.api.request.PurchaseListRequest;
import com.jewellerypos.api.request.SaleListRequest;
import com.jewellerypos.api.request.SaleRequest;
import com.jewellerypos.api.response.SaleResponse;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.service.SaleService;

@Transactional
@Service
public class SaleServiceImpl implements SaleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SaleServiceImpl.class);
	
	private final SaleRepository saleRepository;
	private final ControlRepository controlRepository;
	private final ProductRepository productRepository;
	
	@Autowired
	public SaleServiceImpl(SaleRepository saleRepository,ControlRepository controlRepository,
			ProductRepository productRepository) {
		this.saleRepository = saleRepository;
		this.controlRepository = controlRepository;
		this.productRepository = productRepository;
	}

	@Override
	public SaleResponse createSales(SaleRequest saleReq) {
		SaleResponse response = new SaleResponse();
		
		List<Sale> saleResp = new ArrayList<>();
		Control cntrl = controlRepository.findOne(1l);
		 long saleBillno = cntrl.getSaleBillNo();
		 cntrl.setSaleBillNo(saleBillno+1);
		 controlRepository.save(cntrl);
		LocalDateTime saleDate = LocalDateTime.now();
		
		int i =0;
		for(SaleListRequest sale : saleReq.getSalesList()){
			i++;
			Sale s = new Sale();
			s.setSaleBillNo(saleBillno);
			s.setTagId(sale.getTagId());
			s.setSaleDate(saleDate);
			s.setProductCode(sale.getProductCode());
			s.setPiece(sale.getPiece());
			s.setGrossWeight(sale.getGrossWeight());
			s.setNetWeight(sale.getNetWeight());
			s.setLessWeight(sale.getLessWeight());
			s.setRate(sale.getRate());
			s.setWastage(sale.getWastage());
			s.setMakingCharge(sale.getMakingCharge());
			s.setGrossOrNet(sale.getGrossOrNet());
			s.setSaleTaxPercent(sale.getSaleTaxPercent());
			s.setDiscount(sale.getDiscount());
			s.setOtherCharge(sale.getOtherCharge());
			s.setRoundOfAmount(sale.getRoundOfAmount());
			s.setSaleAddon(sale.getSaleAddon());
			s.setAmount(sale.getAmount());
			s.setSaleType(sale.getSaleType());
			s.setBillStatus(saleReq.getBillStatus());
			s.setDescription(saleReq.getDescription());
			if(i==1) {
				s.setTotalDiscount(saleReq.getTotalDiscount());
				s.setTotalRoundOf(saleReq.getTotalRoundOf());
				s.setTotalAmount(saleReq.getTotalAmount());
			}
			s.setOperatorCode(1);
			s.setCreatedOn(LocalDateTime.now());
			s.setUpdatedOn(LocalDateTime.now());
			saleResp.add(s);
		}
			List<Sale> savedSale = saleRepository.save(saleResp);
			response.setSaleList(savedSale);
			response.setSaleBillno(saleBillno);
			response.setSaleBillDate(saleDate);
		return response;
			
	}

	@Override
	public StatusResponse cancelSales(long saleBillNo) {
		StatusResponse response = new StatusResponse();
		List<Sale> exist = saleRepository.findBySaleBillNo(saleBillNo);
		if(exist == null)
			throw new BillnoNotValidException(ErrorScenario.BILLNO_NOT_VALID,"Sale BillNo : "+saleBillNo);
		if(exist.get(0).getBillStatus().equals("CANCEL"))
			throw new BillAlreadyCancelledException(ErrorScenario.BILL_ALREADY_CANCELED,"Sale BillNo : "+saleBillNo);
		
		List<Sale> cancelBill = new ArrayList<>();
		for(Sale exp : exist){
			exp.setBillStatus("CANCEL");
			exp.setUpdatedOn(LocalDateTime.now());
			cancelBill.add(exp);
		}
		List<Sale> cancelled = saleRepository.save(cancelBill);
		if(cancelled != null){
			response.setStatus(true);
			response.setIdentifier(saleBillNo);
		}
		return response;
	}

	@Override
	public List<Sale> getSalesBySaleBillNo(long saleBillNo) {
		List<Sale> saleList = saleRepository.findBySaleBillNo(saleBillNo);
		if(saleList == null)
			throw new BillnoNotValidException(ErrorScenario.BILLNO_NOT_VALID,"Sale BillNo :"+saleBillNo); 
		return saleList;
	}

	@Override
	public List<Sale> getAllSales(int page, int size) {
		return null;
	}

}
