package com.jewellerypos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.ChitMember;

@Repository
public interface ChitMemberRepository extends JpaRepository<ChitMember, Long>{

    public ChitMember findByChitMemberSno(long chitmemberId);

}
