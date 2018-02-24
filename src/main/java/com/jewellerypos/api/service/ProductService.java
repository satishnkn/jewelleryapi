package com.jewellerypos.api.service;

import java.util.List;

import com.jewellerypos.api.model.Product;
import com.jewellerypos.api.request.ProductRequest;
import com.jewellerypos.api.response.ProductRepsonse;

public interface ProductService {

    public ProductRepsonse createProduct(ProductRequest proReq);

    public ProductRepsonse updateProduct(String productCode, ProductRequest proReq);

    public List<ProductRepsonse> getAllProduct(int page, int size);

}
