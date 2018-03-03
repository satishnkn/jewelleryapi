package com.jewellerypos.api.service;

import com.jewellerypos.api.model.Tag;
import com.jewellerypos.api.response.StatusResponse;

public interface TagService {

	public StatusResponse createTag(Tag tagReq);

	public StatusResponse updateTag(String tagId, Tag tagReq);

}
