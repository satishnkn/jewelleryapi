package com.jewellerypos.api.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jewellerypos.api.model.Tag;
import com.jewellerypos.api.response.StatusResponse;
import com.jewellerypos.api.response.TagResponse;

public interface TagService {

	public TagResponse createTag(Tag tagReq);

	public StatusResponse updateTag(String tagId, Tag tagReq);

	public Page<Tag> getAllTags(int page, int size);

	public Tag getTagById(String tagId);

}
