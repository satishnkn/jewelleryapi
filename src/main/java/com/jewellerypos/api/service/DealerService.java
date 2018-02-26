package com.jewellerypos.api.service;

import com.jewellerypos.api.model.Dealer;
import com.jewellerypos.api.request.DealerRequest;
import com.jewellerypos.api.response.PageDealerResponse;

public interface DealerService {

	public Dealer createDealer(DealerRequest dealerReq);

	public Dealer updateDealer(long dealerId, DealerRequest dealerReq);

	public PageDealerResponse getAllDealer(int page, int size);

	public Dealer getDealerById(long dealerId);


}
