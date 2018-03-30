package com.jewellerypos.api.service.Impl;

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
import com.jewellerypos.api.model.Tag;
import com.jewellerypos.api.repository.ProductRepository;
import com.jewellerypos.api.repository.TagRepository;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.service.TagService;

@Transactional
@Service
public class TagServiceImpl implements TagService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);
	
	private final TagRepository tagRepository;
	private final ProductRepository productRepository;
	
	@Autowired
	public TagServiceImpl(TagRepository tagRepository,ProductRepository productRepository) {
		this.tagRepository = tagRepository;
		this.productRepository = productRepository;
	}

	@Override
	public StatusResponse createTag(Tag tagReq) {
		StatusResponse response = new StatusResponse();
		response.setStatus(false);
		String tagPrefix;
		long tagSeqno;
		Tag plusTag = tagReq;
		Product p = productRepository.findByProductCode(tagReq.getProductCode());
		tagSeqno = p.getTagSeqno() + 1;
		p.setTagSeqno(tagSeqno);
		tagPrefix =  p.getTagPrefix() + tagSeqno;
		productRepository.save(p);
		plusTag.setTagId(tagPrefix);
		plusTag.setCreatedOn(LocalDateTime.now());
		plusTag.setUpdatedOn(LocalDateTime.now());
		plusTag.setTagPlusDate(LocalDateTime.now());
		Tag t =  tagRepository.save(plusTag);
		if(t != null){
			response.setIdentifier(t.getTagNo());
			response.setStatus(true);
		}
		return response;
	}

	@Override
	public StatusResponse updateTag(String tagId,Tag tagReq) {
		StatusResponse response = new StatusResponse();
		response.setStatus(false);
		Tag existTag = tagRepository.findByTagId(tagId);
		if(existTag == null)
			throw new TagNotFoundException(ErrorScenario.TAG_NOT_FOUND, tagId);
		tagReq.setUpdatedOn(LocalDateTime.now());
		tagReq.setTagNo(existTag.getTagNo());
		tagReq.setTagId(tagId);
		Tag t =  tagRepository.save(tagReq);
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
	
	

}
