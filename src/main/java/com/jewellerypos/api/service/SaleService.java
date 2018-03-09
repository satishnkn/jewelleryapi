package com.jewellerypos.api.service;

import java.util.List;

import com.jewellerypos.api.model.Sale;
import com.jewellerypos.api.request.SaleRequest;
import com.jewellerypos.api.response.SaleResponse;
import com.jewellerypos.api.response.StatusResponse;

public interface SaleService {

	public SaleResponse createSales(SaleRequest saleReq);

	public StatusResponse cancelSales(long saleBillNo);

	public List<Sale> getSalesBySaleBillNo(long saleBillNo);

	public List<Sale> getAllSales(int page, int size);


}
