package com.jewellerypos.api.service;

import org.springframework.data.domain.Page;

import com.jewellerypos.api.model.Tag;
import com.jewellerypos.api.request.SaleListRequest;
import com.jewellerypos.api.request.TagRequest;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.response.TagResponse;

public interface TagService {

	public TagResponse createTag(TagRequest tagReq);

	public StatusResponse updateTag(String tagId, TagRequest tagReq);

	public Page<Tag> getAllTags(int page, int size);

	public Tag getTagById(String tagId);

	public SaleListRequest doSaleCalculation(Tag tag, long formulaId);



}
