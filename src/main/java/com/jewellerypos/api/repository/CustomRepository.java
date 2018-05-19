package com.jewellerypos.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomRepository {
    
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    
    
    public List getPurchasevsTag(String startDate,String endDate,int page,int size) {
        page = page + size ;
        String sql = "SELECT P.PURCHASE_NO,P.PURCHASE_BILL_NO,P.PURCHASA_DATE,P.DEALER_ID,P.PRODUCT_CODE,P.PIECE,P.GROSS_WEIGHT,P.NET_WEIGHT,P.LESS_WEIGHT,P.OTHER_CHARGE,P.BILL_REFNO,"
                + " IFNULL(T.PRODUCT_CODE,0) as TPRODUCT_CODE,IFNULL(SUM(T.PIECE),0) AS TPIECE,IFNULL(SUM(T.GROSS_WEIGHT),0) AS TGROSS_WT, IFNULL(SUM(T.NET_WEIGHT) ,0)AS TNET_WT,IFNULL(SUM(T.LESS_WEIGHT) ,0) AS TLESS_WT,IFNULL(T.PURCHASE_NO,0) AS TPURCHASE_NO  FROM PURCHASE AS P"
                + " LEFT JOIN TAG AS T ON P.PURCHASE_NO = T.PURCHASE_NO WHERE  P.PURCHASA_DATE>='"+startDate+" 00:00:00' AND P.PURCHASA_DATE<='"+endDate+" 23:59:59' "
                + " GROUP BY  T.PRODUCT_CODE,T.PURCHASE_NO,"
                + " P.PURCHASE_NO,P.PURCHASE_BILL_NO,P.PURCHASA_DATE,P.DEALER_ID,P.PRODUCT_CODE,P.PIECE,P.GROSS_WEIGHT,P.NET_WEIGHT,P.LESS_WEIGHT,P.OTHER_CHARGE,P.BILL_REFNO";
                //+ " ORDER BY P.PURCHASE_NO LIMIT "+size+" OFFSET "+page;
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql);
    }

}
