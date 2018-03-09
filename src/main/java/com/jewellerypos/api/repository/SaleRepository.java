package com.jewellerypos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	public List<Sale> findBySaleBillNo(long saleBillNo);

}
