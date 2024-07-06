package com.eerp.example.service;


import com.eerp.example.domain.CommoditySku;

import java.util.List;

/**
 * @Author yxb
 * @Description TODO 商品条码Service接口
 * @Date 2020/10/27 13:59
 **/
public interface ICommoditySkuService {


    /**
     * 查询商品条码列表
     *
     * @param commoditySku 商品条码
     * @return 商品条码集合
     */
    public List<CommoditySku> selectCommoditySkuList(CommoditySku commoditySku);


}
