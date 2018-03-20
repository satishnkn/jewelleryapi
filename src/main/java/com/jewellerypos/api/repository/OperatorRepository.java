package com.jewellerypos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.Operator;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long>{

    public Operator findByLoginId(String loginId);

    public Operator findByOperatorCode(long operatorCode);

}
