package com.eerp.example.controller;


import com.eerp.example.domain.CommoditySku;
import com.eerp.example.service.ICommoditySkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eerp.example.core.BaseController;
import com.eerp.example.core.TableDataInfo;
import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

import java.util.Objects;


@RestController
@Slf4j
@RequestMapping("/sku")
public class CommoditySkuController extends BaseController {


    @Autowired
    private ICommoditySkuService commoditySkuService;


    /**
     * 查询商品条码列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody CommoditySku commoditySku) {
        if (Objects.nonNull(commoditySku) && Objects.nonNull(commoditySku.getPageSize())) {
            PageHelper.startPage(commoditySku.getPageNum(), commoditySku.getPageSize());
        }
        List<CommoditySku> list = commoditySkuService.selectCommoditySkuList(commoditySku);
        return getDataTable(list);
    }


}
