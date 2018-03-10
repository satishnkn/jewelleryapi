package com.jewellerypos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.ChitTran;

@Repository
public interface ChitTranRepository extends JpaRepository<ChitTran, Long> {

    public ChitTran findByTranNo(long chittranId);

    public List<ChitTran> findByChitMemberSno(long chitMemberSno);

}
