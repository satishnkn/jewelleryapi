package com.jewellerypos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.SalesFormula;

@Repository
public interface SalesFormulaRepository extends JpaRepository<SalesFormula, Long>{

	public SalesFormula findById(long formulaId);
}
