package com.jewellerypos.api.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.error.MetalNotFoundException;
import com.jewellerypos.api.error.ProductAlreadyExistException;
import com.jewellerypos.api.error.ProductNotFoundException;
import com.jewellerypos.api.model.Metal;
import com.jewellerypos.api.model.Product;
import com.jewellerypos.api.repository.MetalRepository;
import com.jewellerypos.api.repository.ProductRepository;
import com.jewellerypos.api.request.ProductRequest;
import com.jewellerypos.api.response.PageProductResposne;
import com.jewellerypos.api.response.ProductRepsonse;
import com.jewellerypos.api.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
     
    private final ProductRepository productRepository;
    private final MetalRepository  metalRepository;
    
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,MetalRepository  metalRepository) {
        this.productRepository = productRepository;
        this.metalRepository = metalRepository;
    }
    
    Function<Product, ProductRepsonse> resp = new Function<Product, ProductRepsonse>() {

        @Override
        public ProductRepsonse apply(Product t) {
            ProductRepsonse presp = new ProductRepsonse();
            presp.setProductCode(t.getProductCode());
            presp.setProductName(t.getProductName());
            presp.setProductShortName(t.getProductShortName());
            presp.setTaggedYorN(t.getTaggedYorN());
            presp.setWastage(t.getWastage());
            presp.setWeightOrRate(t.getWeightOrRate());
            presp.setMetalName(t.getMetal().getMetalName());
            return presp;
        }
    };
    
    public static <K,V,Q extends K> List<V> transformData( final List<Q> input, final java.util.function.Function<K,V> tfunc ) {
        if( null == input ) {
            return null;
        }
        return input.parallelStream().map(tfunc).collect( Collectors.toList() );
    }
   
    @Override
    public ProductRepsonse createProduct(ProductRequest proReq) {
        ProductRepsonse response= null;
        Product exist = productRepository.findByProductName(proReq.getProductName());
        if(exist != null)
            throw new ProductAlreadyExistException(ErrorScenario.PRODUCT_ALREADY_EXIST, proReq.getProductName());
        
        Product product = new Product();
        product.setProductName(proReq.getProductName());
        product.setProductShortName(proReq.getProductShortName());
        product.setTaggedYorN(proReq.getTaggedYorN());
        product.setWastage(proReq.getWastage());
        product.setWeightOrRate(proReq.getWeightOrRate());
        product.setOperatorCode(proReq.getOperatorCode());
        product.setCreatedOn(LocalDateTime.now());
        product.setUpdatedOn(LocalDateTime.now());
        
        Metal metal = metalRepository.findByMetalId(proReq.getMetalId());
        product.setMetal(metal);
        
        Product npro = productRepository.save(product);
        if(npro != null)
            response = resp.apply(npro) ;
        
        return response;
    }

    @Override
    public ProductRepsonse updateProduct(long productCode, ProductRequest proReq) {
        ProductRepsonse response = null;
        
        Product product = productRepository.findByProductCode(productCode);
        if(product == null)
            throw new ProductNotFoundException(ErrorScenario.PRODUCT_NOT_FOUND, String.valueOf(productCode));
        Product update = new Product();
        if(proReq.getProductName() != null){
            if(!proReq.getProductName().equals(product.getProductName())){
                Product nameExist = productRepository.findByProductName(proReq.getProductName());
                if(nameExist != null)
                    throw new ProductAlreadyExistException(ErrorScenario.PRODUCT_ALREADY_EXIST, proReq.getProductName());
                product.setProductName(proReq.getProductName());
            }
        }
        if(proReq.getProductShortName() != null)
            product.setProductShortName(proReq.getProductShortName());
        if(proReq.getTaggedYorN() != null)
            product.setTaggedYorN(proReq.getTaggedYorN());
        if(proReq.getMetalId() != 0 && proReq.getMetalId() != product.getMetal().getMetalId()){
            Metal m = metalRepository.findByMetalId(proReq.getMetalId());
            if(m == null)
                throw new MetalNotFoundException(ErrorScenario.METAL_NOT_FOUND);
            product.setMetal(m);
        }
        if(proReq.getWastage() != 0)
            product.setWastage(proReq.getWastage());
        if(proReq.getWeightOrRate() != null)
            product.setWeightOrRate(proReq.getWeightOrRate());
        update = productRepository.save(product);
        
        if(update != null)
            response = resp.apply(update);
        return response;
    }

    @Override
    public PageProductResposne getAllProduct(int page,int size) {
    	PageProductResposne response = new PageProductResposne();
        
        if(size == 0){
        	List<Product> productLst = productRepository.findAll();
            response.setProductLst(transformData(productLst , resp));
            response.setNumber(0);
            response.setNumberOfElements(productLst.size());
            response.setSize(0);
            response.setTotalElements(productLst.size());
            response.setTotalPages(1);
        }
        else{
            PageRequest pageRequest = new PageRequest(page, size,
                new Sort(Sort.Direction.DESC, "productName"));
            Page<Product> pagewiseProduct =  productRepository.findAll(pageRequest);
            response.setProductLst(transformData(pagewiseProduct.getContent(), resp)); 
            response.setNumber(pagewiseProduct.getNumber());
            response.setNumberOfElements(pagewiseProduct.getNumberOfElements());
            response.setTotalElements(pagewiseProduct.getTotalElements());
            response.setTotalPages(pagewiseProduct.getTotalPages());
            response.setSize(pagewiseProduct.getSize()); 
            
        }
        
        return response;
    }

	@Override
	public ProductRepsonse getProductByCode(long productCode) {
		Product product = productRepository.findByProductCode(productCode);
		if(product == null )
			throw new ProductNotFoundException(ErrorScenario.PRODUCT_NOT_FOUND,String.valueOf(productCode));
		return resp.apply(product);
	}
    
    
    

}
