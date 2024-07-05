package com.eerp.example.demo.domin;

import com.eerp.example.demo.annotation.Excel;
import com.eerp.example.demo.core.BasicController;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class CommoditySku extends BasicController {
    private static final long serialVersionUID = 1L;

    /**
     * sku_id
     */
    @Id
    @Column(name = "skuId")
    private Long skuId;

    /**
     * 商品条码
     */
    @Excel(name = "商品条码")
    private String skuNo;

    /**
     * 商品货号
     */
    @Excel(name = "商品货号")
    private String commodityNo;

    /**
     * 商品尺码
     */
    @Excel(name = "商品尺码")
    private String commoditySize;

    /**
     * 商品尺码
     */
    @Excel(name = "商品尺码2")
    private String commoditySize2;

    /**
     * 商品牌价
     */
    @Excel(name = "商品牌价")
    private BigDecimal commodityPrice;
    /**
     * 商品牌价
     */
    @Excel(name = "折扣")
    private BigDecimal commodityDiscount;
    /**
     * 商品牌价
     */
    @Excel(name = "出售折扣")
    private BigDecimal sellDiscount;

    /**
     * 商品重量
     */
    @Excel(name = "商品重量")
    private BigDecimal commodityWeight;

    /**
     * 商品品牌
     */
    @Excel(name = "商品品牌")
    private String commodityBrand;

    /**
     * 商品平台渠道
     */
    @Excel(name = "商品平台渠道")
    private String commodityChannel;
    /**
     * 租户Id
     */
    private Long tenantId;

    private Integer pageNum;
    private Integer pageSize;
}
