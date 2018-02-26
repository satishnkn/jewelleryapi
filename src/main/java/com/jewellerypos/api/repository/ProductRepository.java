package com.jewellerypos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewellerypos.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findByProductName(String productName);

    public Product findByProductCode(long productCode);

}
