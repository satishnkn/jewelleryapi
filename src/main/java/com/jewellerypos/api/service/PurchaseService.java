package com.jewellerypos.api.service;

import java.util.List;

import com.jewellerypos.api.model.Purchase;
import com.jewellerypos.api.request.PurchaseRequest;
import com.jewellerypos.api.response.PurchaseResponse;
import com.jewellerypos.api.response.StatusResponse;

public interface PurchaseService {

	public PurchaseResponse createPurchase(PurchaseRequest purchaseReq);

	public PurchaseResponse updatePurchase(long purchaseBillNo, PurchaseRequest purchaseReq);

	public StatusResponse cancelPurchase(long purchaseBillNo);

	public List<Purchase> getPurchaseByPurchaseBillNo(long purchaseBillNo);

}