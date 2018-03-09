package com.jewellerypos.api.service;

import java.util.List;

import com.jewellerypos.api.model.Metal;
import com.jewellerypos.api.model.MetalRateHistory;
import com.jewellerypos.api.request.MetalRateHistoryRequest;

public interface MetalRateHistoryService {

    public MetalRateHistory createHistory(MetalRateHistoryRequest metalRateRequest);

	public List<Metal> getAllMetalRate();

	public Metal createMetal(Metal metal);

}
