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
import com.jewellerypos.api.error.ProductNotFoundException;
import com.jewellerypos.api.model.Control;
import com.jewellerypos.api.model.Product;
import com.jewellerypos.api.model.Purchase;
import com.jewellerypos.api.repository.ControlRepository;
import com.jewellerypos.api.repository.ProductRepository;
import com.jewellerypos.api.repository.PurchaseRepository;
import com.jewellerypos.api.request.PurchaseListRequest;
import com.jewellerypos.api.request.PurchaseRequest;
import com.jewellerypos.api.response.PurchaseResponse;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.restcontroller.PurchaseController;
import com.jewellerypos.api.service.PurchaseService;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseServiceImpl.class);
	
	private final PurchaseRepository purchaseRepository;
	private final ControlRepository controlRepository;
	private final ProductRepository productRepository;
	
	@Autowired
	public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
			ControlRepository controlRepository,ProductRepository productRepository) {
		this.purchaseRepository = purchaseRepository;
		this.controlRepository = controlRepository;
		this.productRepository = productRepository;
	}

	@Override
	public PurchaseResponse createPurchase(PurchaseRequest purchaseReq) {
		
		PurchaseResponse response = new PurchaseResponse();
		
		List<Purchase> purchaseresp = new ArrayList<>();
		Control cntrl = controlRepository.findOne(1l);
		 long purchaseBillno = cntrl.getPurchaseBillno();
		 cntrl.setPurchaseBillno(purchaseBillno+1);
		 controlRepository.save(cntrl);
		LocalDateTime purchaseDate = LocalDateTime.now();
		int i =0;
		for(PurchaseListRequest purchase : purchaseReq.getPurchaseList()){
			i++;
			Purchase p = new Purchase();
			p.setDealerId(purchaseReq.getDealerId());
			p.setPurchaseBillNo(purchaseBillno);
			p.setPurchaseDate(purchaseDate);
			p.setProductCode(purchase.getProductCode());
			p.setPiece(purchase.getPiece());
			p.setGrossWeight(purchase.getGrossWeight());
			p.setNetWeight(purchase.getNetWeight());
			p.setLessWeight(purchase.getLessWeight());
			p.setRate(purchase.getRate());
			p.setWastage(purchase.getWastage());
			p.setMakingCharge(purchase.getMakingCharge());
			p.setGrossOrNet(purchase.getGrossOrNet());
			p.setPurchaseTaxPercent(purchase.getPurchaseTaxPercent());
			p.setDiscount(purchase.getDiscount());
			p.setOtherCharge(purchase.getOtherCharge());
			p.setRoundOfAmount(purchase.getRoundOfAmount());
			p.setAmount(purchase.getAmount());
			p.setPurchaseType(purchase.getPurchaseType());
			p.setPurchaseAddon(purchase.getPurchaseAddon());
			p.setBillRefNo(purchaseReq.getBillRefNo());
			p.setBillRefDate(purchaseReq.getBillRefDate());
			p.setBillStatus(purchaseReq.getBillStatus());
			p.setDescription(purchaseReq.getDescription());
			if(i==1) {
				p.setTotalDiscount(purchaseReq.getTotalDiscount());
				p.setTotalRoundOf(purchaseReq.getTotalRoundOf());
				p.setTotalAmount(purchaseReq.getTotalAmount());
			}
			p.setOperatorCode(1);
			p.setCreatedOn(LocalDateTime.now());
			p.setUpdatedOn(LocalDateTime.now());
			purchaseresp.add(p);
		}
			response.setPurchaseList(purchaseresp);
			response.setPurchaseBillno(purchaseBillno);
			response.setPurchaseBillDate(purchaseDate);
		return response;
	}

	@Override
	public PurchaseResponse updatePurchase(long purchaseBillNo, PurchaseRequest purchaseReq) {
		List<Purchase> exist = purchaseRepository.findByPurchaseBillNo(purchaseBillNo);
		if(exist == null)
			throw new ProductNotFoundException();
		if(exist.get(0).getBillStatus().equals("CANCEL"))
			throw new ProductNotFoundException("Bill already Cancelled");
		
		PurchaseResponse response = createPurchase(purchaseReq);
		
		List<Purchase> cancelBill = new ArrayList<>();
		for(Purchase exp : exist){
			exp.setBillRefNo("Edit - "+String.valueOf(response.getPurchaseBillno()));
			exp.setBillRefDate(response.getPurchaseBillDate());
			exp.setBillStatus("CANCEL");
			exp.setUpdatedOn(LocalDateTime.now());
			cancelBill.add(exp);
		}
		purchaseRepository.save(cancelBill);
		
		return response;
	}

	@Override
	public StatusResponse cancelPurchase(long purchaseBillNo) {
		
		StatusResponse response = new StatusResponse();
		List<Purchase> exist = purchaseRepository.findByPurchaseBillNo(purchaseBillNo);
		if(exist == null)
			throw new BillnoNotValidException(ErrorScenario.BILLNO_NOT_VALID,"Purchase BillNo : "+purchaseBillNo);
		if(exist.get(0).getBillStatus().equals("CANCEL"))
			throw new BillAlreadyCancelledException(ErrorScenario.BILL_ALREADY_CANCELED,"Purchase BillNo : "+purchaseBillNo);
		
		List<Purchase> cancelBill = new ArrayList<>();
		for(Purchase exp : exist){
			exp.setBillStatus("CANCEL");
			exp.setUpdatedOn(LocalDateTime.now());
			cancelBill.add(exp);
		}
		List<Purchase> cancelled = purchaseRepository.save(cancelBill);
		if(cancelled != null){
			response.setStatus(true);
			response.setIdentifier(purchaseBillNo);
			
		}
		return response;
	}

	@Override
	public List<Purchase> getPurchaseByPurchaseBillNo(long purchaseBillNo) {
		
		List<Purchase> purchaseList = purchaseRepository.findByPurchaseBillNo(purchaseBillNo);
		if(purchaseList == null)
			throw new BillnoNotValidException(ErrorScenario.BILLNO_NOT_VALID,"Purchase BillNo :"+purchaseBillNo); 
		return purchaseList;
	}

}
