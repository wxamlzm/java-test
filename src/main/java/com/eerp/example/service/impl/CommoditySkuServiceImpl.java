package com.eerp.example.service.impl;


import com.eerp.example.domain.CommoditySku;
import com.eerp.example.mapper.CommoditySkuMapper;
import com.eerp.commodity.model.dto.commodityRequest;
import com.eerp.commodity.model.dto.commodityResponse;
import com.eerp.commodity.model.vo.OrderVo;
import com.eerp.commodity.model.vo.TaoBaoSkuView;
import com.eerp.example.service.ICommoditySkuService;
import com.eerp.commodity.utils.*;
import com.eerp.commodity.utils.text.UnicodeConvertUtils;
import com.eerp.commodity.utils.uuid.UniqueKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @Author yxb
 * @Description TODO 商品条码Service业务层处理
 * @Date 2020/10/27 13:59
 **/
@Slf4j
@Service
public class CommoditySkuServiceImpl implements ICommoditySkuService {

    @Autowired
    private CommoditySkuMapper commoditySkuMapper;


    /**
     * 查询商品条码列表
     *
     * @param commoditySku 商品条码
     * @return 商品条码
     */
    @Override
    public List<CommoditySku> selectCommoditySkuList(CommoditySku commoditySku) {
        return commoditySkuMapper.selectCommoditySkuList(commoditySku);
    }


}
