package com.jewellerypos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.ChitGroup;

@Repository
public interface ChitGroupRepository extends JpaRepository<ChitGroup, Long>{

   public  ChitGroup findByChitGroupSno(long chitgroupId);

   public List<ChitGroup> findByChitGroupStatus(String status);

}
