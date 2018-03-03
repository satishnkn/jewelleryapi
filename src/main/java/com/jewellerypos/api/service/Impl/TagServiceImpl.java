package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.error.TagNotFoundException;
import com.jewellerypos.api.model.Tag;
import com.jewellerypos.api.repository.TagRepository;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.service.TagService;

@Transactional
@Service
public class TagServiceImpl implements TagService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);
	
	private final TagRepository tagRepository;
	
	@Autowired
	public TagServiceImpl(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public StatusResponse createTag(Tag tagReq) {
		StatusResponse response = new StatusResponse();
		response.setStatus(false);
		Tag plusTag = tagReq;
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
		existTag.setUpdatedOn(LocalDateTime.now());
		Tag t =  tagRepository.save(existTag);
		if(t != null){
			response.setIdentifier(t.getTagNo());
			response.setStatus(true);
		}
		return response;
	}
	
	

}
