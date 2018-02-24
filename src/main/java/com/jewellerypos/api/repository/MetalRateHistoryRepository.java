package com.jewellerypos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.MetalRateHistory;

@Repository
public interface MetalRateHistoryRepository extends JpaRepository<MetalRateHistory, Long>{

}
