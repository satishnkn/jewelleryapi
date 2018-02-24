package com.jewellerypos.api.service;

import com.jewellerypos.api.model.MetalRateHistory;
import com.jewellerypos.api.request.MetalRateHistoryRequest;

public interface MetalRateHistoryService {

    public MetalRateHistory createHistory(MetalRateHistoryRequest metalRateRequest);

}
