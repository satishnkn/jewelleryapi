package com.jewellerypos.api.service;

import java.util.List;

import com.jewellerypos.api.model.SalesFormula;
import com.jewellerypos.api.request.SalesFormulaRequest;

public interface SalesFormulaService {

	public SalesFormula createFormula(SalesFormulaRequest salesFormulaRequest);

	public List<SalesFormula> findAllFormula();

}
