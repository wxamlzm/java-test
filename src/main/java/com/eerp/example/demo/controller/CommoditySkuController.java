package com.eerp.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.eerp.example.demo.core.BasicController;

import java.util.*;


@RestController
@Slf4j
@RequestMapping("/sku")
public class CommoditySkuController extends BasicController {


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
