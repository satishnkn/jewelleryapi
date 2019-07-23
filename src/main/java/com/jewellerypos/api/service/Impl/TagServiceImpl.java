package com.jewellerypos.api.service.Impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.error.TagNotFoundException;
import com.jewellerypos.api.model.Product;
import com.jewellerypos.api.model.SalesFormula;
import com.jewellerypos.api.model.Tag;
import com.jewellerypos.api.repository.ProductRepository;
import com.jewellerypos.api.repository.SalesFormulaRepository;
import com.jewellerypos.api.repository.TagRepository;
import com.jewellerypos.api.request.SaleListRequest;
import com.jewellerypos.api.request.TagRequest;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.response.TagResponse;
import com.jewellerypos.api.service.TagService;

@Transactional
@Service
public class TagServiceImpl implements TagService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);
	
	private final TagRepository tagRepository;
	private final ProductRepository productRepository;
	private final SalesFormulaRepository salesFormulaRepository;
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	@Autowired
	public TagServiceImpl(TagRepository tagRepository,
			ProductRepository productRepository,SalesFormulaRepository salesFormulaRepository) {
		this.tagRepository = tagRepository;
		this.productRepository = productRepository;
		this.salesFormulaRepository =salesFormulaRepository;
	}
	
	

	@Override
	public TagResponse createTag(TagRequest tagReq) {
		
	    TagResponse response = new TagResponse();
		response.setStatus(false);
		String tagPrefix;
		long tagSeqno;
		Tag plusTag = new Tag();
		
		Product p = productRepository.findByProductCode(tagReq.getProductCode());
		tagSeqno = p.getTagSeqno() + 1;
		p.setTagSeqno(tagSeqno);
		tagPrefix =  p.getTagPrefix() + tagSeqno;
		productRepository.save(p);
		plusTag.setTagId(tagPrefix);
		plusTag.setCreatedOn(LocalDateTime.now());
		plusTag.setUpdatedOn(LocalDateTime.now());
		plusTag.setTagPlusDate(LocalDateTime.now());
		
		plusTag.setProductCode(tagReq.getProductCode());
		plusTag.setPiece(tagReq.getPiece());
		plusTag.setGrossWeight(tagReq.getGrossWeight());
		plusTag.setLessWeight(tagReq.getLessWeight());
		plusTag.setNetWeight(tagReq.getNetWeight());
		plusTag.setPurchaseRate(tagReq.getPurchaseRate());
		plusTag.setPurchaseNo(tagReq.getPurchaseNo());
		plusTag.setWastage(tagReq.getWastage());		
		plusTag.setWastageWeight(calculateWastageweight(tagReq.getNetWeight(),tagReq.getWastage()));
		plusTag.setMakingCharge(tagReq.getMakingCharge());
		plusTag.setGrossOrNet(tagReq.getGrossOrNet());
		plusTag.setDiscount(tagReq.getDiscount());
		plusTag.setOtherCharge(tagReq.getOtherCharge());
		plusTag.setTagAddon(tagReq.getTagAddon());
		plusTag.setOperatorCode(tagReq.getOperatorCode());
		
		
		Tag t =  tagRepository.save(plusTag);
		if(t != null){
		    response.setTagId(t.getTagId());
			response.setTagNo(t.getTagNo());
			response.setStatus(true);
		}
		return response;
	}
	
	private double calculateWastageweight(double netWeight,double wastage) {
		NumberFormat formatter = new DecimalFormat("#0.000");
		double wastagewt = netWeight * (wastage/100);
		return Double.parseDouble(formatter.format(wastagewt));
	}

	@Override
	public StatusResponse updateTag(String tagId,TagRequest tagReq) {
		StatusResponse response = new StatusResponse();
		response.setStatus(false);
		Tag existTag = tagRepository.findByTagId(tagId);
		if(existTag == null)
			throw new TagNotFoundException(ErrorScenario.TAG_NOT_FOUND, tagId);
		
		existTag.setProductCode(tagReq.getProductCode());
		existTag.setPiece(tagReq.getPiece());
		existTag.setGrossWeight(tagReq.getGrossWeight());
		existTag.setLessWeight(tagReq.getLessWeight());
		existTag.setNetWeight(tagReq.getNetWeight());
		existTag.setPurchaseRate(tagReq.getPurchaseRate());
		existTag.setPurchaseNo(tagReq.getPurchaseNo());
		existTag.setWastage(tagReq.getWastage());		
		existTag.setWastageWeight(calculateWastageweight(tagReq.getNetWeight(),tagReq.getWastage()));
		existTag.setMakingCharge(tagReq.getMakingCharge());
		existTag.setGrossOrNet(tagReq.getGrossOrNet());
		existTag.setDiscount(tagReq.getDiscount());
		existTag.setOtherCharge(tagReq.getOtherCharge());
		existTag.setTagAddon(tagReq.getTagAddon());
		existTag.setOperatorCode(tagReq.getOperatorCode());
		
		existTag.setUpdatedOn(LocalDateTime.now());
		
		Tag t =  tagRepository.save(existTag);
		if(t != null){
			response.setIdentifier(t.getTagNo());
			response.setStatus(true);
		}
		return response;
	}

	@Override
	public Page<Tag> getAllTags(int page, int size) {
		 PageRequest pageRequest = new PageRequest(page, size,
	                new Sort(Sort.Direction.DESC, "tagPlusDate"));
		Page<Tag> tagview = tagRepository.findAll(pageRequest);
		return tagview;
	}

	@Override
	public Tag getTagById(String tagId) {
		Tag tag = tagRepository.findByTagId(tagId);
		if(tag == null)
			throw new TagNotFoundException(ErrorScenario.TAG_NOT_FOUND, tagId);
		return tag;
	}

	@Override
	public SaleListRequest doSaleCalculation(Tag tag,long formulaId) {
		
		Product product = productRepository.findByProductCode(tag.getProductCode());
		double rate = product.getMetal().getMetalRate();
		
		SalesFormula salesFormula = salesFormulaRepository.findById(formulaId);
		double wastagewt = 0;
		SaleListRequest request = new SaleListRequest();
		request.setTagId(tag.getTagId());
		request.setProductCode(tag.getProductCode());
		request.setGrossWeight(tag.getGrossWeight());
		request.setLessWeight(tag.getLessWeight());
		request.setNetWeight(tag.getNetWeight());
		request.setPiece(tag.getPiece());
		request.setDiscount(tag.getDiscount());
		request.setGrossOrNet(tag.getGrossOrNet());
		
		request.setOtherCharge(tag.getOtherCharge());
		request.setRate(rate);	
		if(salesFormula.getWastage().equals("Y"))
			wastagewt = calculateWastageweight(tag.getNetWeight(),tag.getWastage() );
		if(salesFormula.getMakingCharge().equals("Y")) 
			request.setMakingCharge(tag.getMakingCharge());
		else
			request.setMakingCharge(0);	
			
		request.setAmount(calculateAmount(rate, tag.getNetWeight(),
				wastagewt, tag.getMakingCharge()));
		request.setSaleTaxPercent(3);
		if(salesFormula.getTax().equals("Y"))
			request.setSaleTaxAmount(calculateTax(request.getAmount(), request.getSaleTaxPercent()));
		else {
			request.setSaleTaxAmount(Double.parseDouble(formatter.format(request.getAmount())));
		}
		
		
		//request.setRoundOfAmount(roundOfAmount);
		request.setSaleType("PRE");
		request.setWastage(tag.getWastage());
		request.setWastageWeight(tag.getWastageWeight());
		
		
		
		
		
		/*String AMOUNT = "( NETWT + WASTAGEWT ) * GOLDRATE + MAKINGCHARGE" ;
		String VATAMOUNT = "( AMOUNT * ( VAT / 100 ) )" ;
		String TOTALAMOUNT = "AMOUNT + VATAMOUNT" ;
		
		String[] formula = TOTALAMOUNT.split(" ");
		while(formula.length < 0)*/
		return request;
	}
	
	
	
	private double calculateTax(double amount,double taxpercent) {
		double taxAmount = amount * (taxpercent/100);
		return Double.parseDouble(formatter.format(taxAmount));
	}
	
	private double calculateAmount(double rate,double netwt,double wastagewt,double makingcharge) {
		double goldamount = ((netwt+wastagewt) * rate)+makingcharge;
		return Double.parseDouble(formatter.format(goldamount));
	}
	
	

}
