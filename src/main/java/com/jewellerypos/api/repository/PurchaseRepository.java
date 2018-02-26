package com.jewellerypos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

	public List<Purchase> findByPurchaseBillNo(long purchaseBillNo);

}
