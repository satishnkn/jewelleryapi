package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.BillAlreadyCancelledException;
import com.jewellerypos.api.error.BillnoNotValidException;
import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.error.ProductNotFoundException;
import com.jewellerypos.api.error.PurchaseNotFoundException;
import com.jewellerypos.api.model.Control;
import com.jewellerypos.api.model.Product;
import com.jewellerypos.api.model.Purchase;
import com.jewellerypos.api.model.PurchaseAddon;
import com.jewellerypos.api.repository.ControlRepository;
import com.jewellerypos.api.repository.CustomRepository;
import com.jewellerypos.api.repository.ProductRepository;
import com.jewellerypos.api.repository.PurchaseRepository;
import com.jewellerypos.api.request.PurchaseListRequest;
import com.jewellerypos.api.request.PurchaseRequest;
import com.jewellerypos.api.response.PageProductResposne;
import com.jewellerypos.api.response.PagePurchaseResponse;
import com.jewellerypos.api.response.PurchaseResponse;
import com.jewellerypos.api.response.PurchasevsTagResponse;
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
	private final CustomRepository customRepository;
	
	@Autowired
	public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
			ControlRepository controlRepository,ProductRepository productRepository,CustomRepository customRepository) {
		this.purchaseRepository = purchaseRepository;
		this.controlRepository = controlRepository;
		this.productRepository = productRepository;
		this.customRepository = customRepository ;
	}

	@Override
	public Purchase createPurchase(PurchaseRequest purchase) {
		
		//PurchaseResponse response = new PurchaseResponse();
		
		//List<Purchase> purchaseresp = new ArrayList<>();
		
		
		Control cntrl = controlRepository.findOne(1l);
		 long purchaseBillno = cntrl.getPurchaseBillno();
		 cntrl.setPurchaseBillno(purchaseBillno+1);
		 controlRepository.save(cntrl);
		LocalDateTime purchaseDate = LocalDateTime.now();
		
		Purchase p = new Purchase();
		p.setDealerId(purchase.getDealerId());
		p.setPurchaseBillNo(purchaseBillno);
		p.setPurchaseDate(purchaseDate);
		p.setPiece(purchase.getPiece());
		p.setGrossWeight(purchase.getGrossWeight());
		p.setNetWeight(purchase.getNetWeight());
		p.setLessWeight(purchase.getLessWeight());
		p.setGrossOrNet(purchase.getGrossOrNet());
		p.setPurchaseTaxPercent(purchase.getPurchaseTaxPercent());
		p.setOtherCharge(purchase.getOtherCharge());
		p.setPurchaseType(purchase.getPurchaseType());
		p.setBillRefNo(purchase.getBillRefNo());
		p.setBillRefDate(purchase.getBillRefDate());
		p.setBillStatus(purchase.getBillStatus());
		p.setDescription(purchase.getDescription());
		p.setTotalDiscount(purchase.getTotalDiscount());
		p.setTotalRoundOf(purchase.getTotalRoundOf());
		p.setTotalAmount(purchase.getTotalAmount());
		p.setOperatorCode(1);
		p.setCreatedOn(LocalDateTime.now());
		p.setUpdatedOn(LocalDateTime.now());
		Purchase savedPurchase = purchaseRepository.save(p);
		
		return savedPurchase;
		
		/*int i =0;
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
		List<Purchase> savedPurchase = purchaseRepository.save(purchaseresp);
			response.setPurchaseList(savedPurchase);
			response.setPurchaseBillno(purchaseBillno);
			response.setPurchaseBillDate(purchaseDate);
		return response;*/
	}

	@Override
	public Purchase updatePurchase(long purchaseBillNo, PurchaseRequest purchaseReq) {
		List<Purchase> exist = purchaseRepository.findByPurchaseBillNo(purchaseBillNo);
		if(exist.isEmpty())
			throw new PurchaseNotFoundException(ErrorScenario.PURCHASE_NOT_FOUND,String.valueOf(purchaseBillNo));
		if(exist.get(0).getBillStatus().equals("CANCEL"))
			throw new BillAlreadyCancelledException(ErrorScenario.BILL_ALREADY_CANCELED,"Purchase BillNo : "+purchaseBillNo);
		Purchase response = createPurchase(purchaseReq);
		List<Purchase> cancelBill = new ArrayList<>();
		for(Purchase exp : exist){
			exp.setBillRefNo("Edit - "+String.valueOf(response.getPurchaseBillNo()));
			exp.setBillRefDate(response.getPurchaseDate());
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
		if(exist.isEmpty())
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

	@Override
	public PagePurchaseResponse getAllPurchase(int page, int size) {
		
      PagePurchaseResponse response = new PagePurchaseResponse();
        
        if(size == 0){
        	List<Purchase> purchaseLst = purchaseRepository.findAll();
            response.setPurchaseLst(purchaseLst);
            response.setNumber(0);
            response.setNumberOfElements(purchaseLst.size());
            response.setSize(0);
            response.setTotalElements(purchaseLst.size());
            response.setTotalPages(1);
        }
        else{
            PageRequest pageRequest = new PageRequest(page, size,
                new Sort(Sort.Direction.DESC, "purchaseNo"));
            Page<Purchase> pagewisePurchase =  purchaseRepository.findAll(pageRequest);
            response.setPurchaseLst(pagewisePurchase.getContent());
            response.setNumber(pagewisePurchase.getNumber());
            response.setNumberOfElements(pagewisePurchase.getNumberOfElements());
            response.setTotalElements(pagewisePurchase.getTotalElements());
            response.setTotalPages(pagewisePurchase.getTotalPages());
            response.setSize(pagewisePurchase.getSize()); 
            
        }
		return response;
	}

    @Override
    public List<PurchasevsTagResponse> getPurchasevsTag(String startDate, String endDate,int page, int size) {
        
        Map<String, Object> record;
        System.out.println("test 12345wwwwww");
        List purchasevstagList = customRepository.getPurchasevsTag(startDate,endDate,page, size);
        System.out.println("test 12345");
        List<PurchasevsTagResponse> response = new ArrayList<>();
        for(Iterator itr = purchasevstagList.iterator(); itr.hasNext();) {
            record = (Map) itr.next();
            PurchasevsTagResponse resp = new PurchasevsTagResponse();
            resp.setPurchaseNo(record.get("PURCHASE_NO").toString());
            resp.setPurchaseBillNo(record.get("PURCHASE_BILL_NO").toString());
            resp.setPurchaseDate(record.get("PURCHASA_DATE").toString());
            resp.setDealerId(record.get("DEALER_ID").toString());
            resp.setProductCode(record.get("PRODUCT_CODE").toString());
            resp.setPiece(record.get("PIECE").toString());
            resp.setGrossWt(record.get("GROSS_WEIGHT").toString());
            resp.setNetWt(record.get("NET_WEIGHT").toString());
            resp.setLessWt(record.get("LESS_WEIGHT").toString());
            resp.setOtherCharge(record.get("OTHER_CHARGE").toString());
            resp.setBillrefNo(record.get("BILL_REFNO").toString());
            resp.setTproductCode(record.get("TPRODUCT_CODE").toString());
            resp.setTproductCode(record.get("TPIECE").toString());
            resp.setTgrossWt(record.get("TGROSS_WT").toString());
            resp.setTnetWt(record.get("TNET_WT").toString());
            resp.setTlessWt(record.get("TLESS_WT").toString());
            resp.setTpurchaseNo(record.get("TPURCHASE_NO").toString());
            response.add(resp);     
            }
		return response;
        }

	@Override
	public Purchase getPurchaseByPurchaseNo(long purchaseNo) {
		
		Purchase purchase = purchaseRepository.findByPurchaseNo(purchaseNo);
		if(purchase == null)
			throw new BillnoNotValidException(ErrorScenario.BILLNO_NOT_VALID,"Purchase No :"+purchaseNo); 
		return purchase;
	}
            
        
        /*public JSONArray beaconsList(String search) {   
            JSONArray beaconsAray = new JSONArray();
            JSONObject obj;
            Map<String, Object> record;
            List beaconslist = customBeaconMasterRepository.getAllBeacons(search);
            for (Iterator itr = beaconslist.iterator(); itr.hasNext();) {
                record = (Map) itr.next();
                obj = new JSONObject();
                for (Map.Entry<String, Object> entry : record.entrySet()) {                 
                    obj.put(entry.getKey(), entry.getValue().toString());               
                }
                beaconsAray.add(obj);
            }
            return beaconsAray; 
        }*/
        
        
    

}
