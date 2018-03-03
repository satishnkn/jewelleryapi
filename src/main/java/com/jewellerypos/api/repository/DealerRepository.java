package com.jewellerypos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {

	public Dealer findByDealerId(long dealerId);

	public Dealer findByMobileNo(String mobileNo);

}
