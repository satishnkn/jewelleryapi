package com.jewellerypos.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomRepository {
    
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    
    
    public List getPurchasevsTag(int page,int size) {
        page = page + size ;
        String sql = "SELECT P.PURCHASE_NO,P.PURCHASE_BILL_NO,P.PURCHASA_DATE,P.DEALER_ID,P.PRODUCT_CODE,P.PIECE,P.GROSS_WEIGHT,P.NET_WEIGHT,P.LESS_WEIGHT,P.OTHER_CHARGE,P.BILL_REFNO,"
                + " IFNULL(T.PRODUCT_CODE,0),IFNULL(SUM(T.PIECE),0) AS PIECE,IFNULL(SUM(T.GROSS_WEIGHT),0) AS GRS_WT, IFNULL(SUM(T.NET_WEIGHT) ,0)AS NET_WT,IFNULL(SUM(T.LESS_WEIGHT) ,0) AS LESS_WT,T.PURCHASE_NO  FROM PURCHASE AS P"
                + " LEFT JOIN TAG AS T ON P.PURCHASE_NO = T.PURCHASE_NO GROUP BY  T.PRODUCT_CODE,T.PURCHASE_NO,"
                + " P.PURCHASE_NO,P.PURCHASE_BILL_NO,P.PURCHASA_DATE,P.DEALER_ID,P.PRODUCT_CODE,P.PIECE,P.GROSS_WEIGHT,P.NET_WEIGHT,P.LESS_WEIGHT,P.OTHER_CHARGE,P.BILL_REFNO  ORDER BY P.PURCHASE_NO LIMIT "+size+" OFFSET "+page;
        return jdbcTemplate.queryForList(sql);
    }

}
