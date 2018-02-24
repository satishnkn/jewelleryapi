package com.jewellerypos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.Metal;

@Repository
public interface MetalRepository extends JpaRepository<Metal, Long> {

   public  Metal findByMetalId(long metalId);

}
