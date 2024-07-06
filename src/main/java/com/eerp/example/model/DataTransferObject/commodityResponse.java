package com.eerp.example.model.DataTransferObject;

import com.alibaba.excel.annotation.ExcelProperty;
import com.eerp.example.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface commodityResponse {
    @Data
    @ApiModel("导入adidas官网商品数据")
    class AdidasImport {

        @ApiModelProperty("商品id")
        @Excel(name = "商品id")
        private String productId;

        //1 新增 2存量
        private Integer isAdd;

        @ApiModelProperty("商品sku")
        @Excel(name = "商品sku")
        private String commoditySku;

        @ApiModelProperty("货号")
        @Excel(name = "货号")
        private String commodityNo;


        @ApiModelProperty("商品分类")
        @Excel(name = "商品分类")
        private String productCategory;


        @ApiModelProperty("商品标题")
        @Excel(name = "商品标题")
        private String productTitle;


        @ApiModelProperty("商品二级标题")
        @Excel(name = "商品二级标题")
        private String productSecondTitle;


        @ApiModelProperty("商品状态")
        @Excel(name = "商品状态")
        private String productStatus;

        @ApiModelProperty("商品地址")
        @Excel(name = "商品地址")
        private String detailUrl;

        @ApiModelProperty("商品折扣信息")
        @Excel(name = "商品折扣信息")
        private String productDiscount;

        @ApiModelProperty("颜色")
        @Excel(name = "颜色")
        private String color;

        @ApiModelProperty("尺码")
        @Excel(name = "尺码")
        private String commoditySize;

        @ApiModelProperty("原尺码")
        private String rawCommoditySize;

        @ApiModelProperty("原货号")
        private String rawCommodityNo;

        @ApiModelProperty("尺码说明")
        @Excel(name = "尺码说明")
        private String commoditySizeMark;

        @ApiModelProperty("库存")
        @Excel(name = "库存")
        private Integer stock;

        @ApiModelProperty("原价")
        @Excel(name = "原价")
        private BigDecimal productOriginalPrice;

        @ApiModelProperty("当前价格")
        @Excel(name = "当前价格")
        private BigDecimal productCurrentPrice;

        @ApiModelProperty("是否限购")
        @Excel(name = "是否限购")
        private String buyLimit;

        @ApiModelProperty("是否可以用优惠券")
        @Excel(name = "是否可以用优惠券")
        private String useCoupon;

        @ApiModelProperty("促销信息")
        @Excel(name = "促销信息")
        private String promotionMessage;

        @ApiModelProperty("商品标签")
        @Excel(name = "商品标签")
        private String productLable;

        @ApiModelProperty("更新时间")
        @Excel(name = "更新时间")
        private Date updateTime;

        @ApiModelProperty(name = "批次号")
        private String batch;

        private String productImages; // 图片

        private String detailImages; // 详情图片

        private String detailDesc; // 详情描述

        private String promotionsInactive;
    }

    @Data
    @ApiModel("导入nike官网商品数据")
    class NikeImport {

        @ApiModelProperty("pid")
        @Excel(name = "pid")
        private String pid;

        //1 新增 2存量
        private Integer isAdd;

        @ApiModelProperty("商品sku")
        @Excel(name = "商品sku")
        private String commoditySku;

        @ApiModelProperty("标题")
        @Excel(name = "标题")
        private String title;

        @ApiModelProperty("分类")
        @Excel(name = "分类")
        private String productCategory;

        @ApiModelProperty("商品地址")
        @Excel(name = "商品地址")
        private String detailUrl;

        @ApiModelProperty("商品类型")
        @Excel(name = "商品类型")
        private String productType;

        @ApiModelProperty("商品状态")
        @Excel(name = "商品状态")
        private String productStatus;

        @ApiModelProperty("货号")
        @Excel(name = "货号")
        private String commodityNo;

        @ApiModelProperty("原货号")
        private String rawCommodityNo;

        @ApiModelProperty("颜色")
        @Excel(name = "颜色")
        private String color;

        @ApiModelProperty("尺码")
        @Excel(name = "尺码")
        private String commoditySize;

        @ApiModelProperty("原尺码")
        private String rawCommoditySize;

        @ApiModelProperty("货源状态")
        @Excel(name = "货源状态")
        private String goodsStock;

        @ApiModelProperty("原价")
        @Excel(name = "原价")
        private BigDecimal productOriginalPrice;

        @ApiModelProperty("当前价格")
        @Excel(name = "当前价格")
        private BigDecimal productCurrentPrice;

        @ApiModelProperty("会员价")
        @Excel(name = "会员价")
        private BigDecimal employeePrice;

        @ApiModelProperty("库存")
        @Excel(name = "库存")
        private Integer stock;

        @ApiModelProperty("是否有优惠")
        @Excel(name = "是否有优惠")
        private String isDiscounted;

        @ApiModelProperty("是否可用优惠券")
        @Excel(name = "是否可用优惠券")
        private String useCoupon;

        @ApiModelProperty("是否限购")
        @Excel(name = "是否限购")
        private String buyLimit;

        @ApiModelProperty("更新时间")
        @Excel(name = "更新时间")
        private Date updateTime;

        @ApiModelProperty(name = "批次号")
        private String batch;

        @ApiModelProperty("活动优惠信息")
        @Excel(name = "活动优惠信息")
        private String promotions;

        private String productImages; // 图片

        private String detailImages; // 详情图片

        private String detailDesc; // 详情描述

    }

    @Data
    @ApiModel("导入yougou官网商品数据")
    class YouGouImport {

        @ApiModelProperty("商品id")
        @Excel(name = "商品id")
        private String pid;

        @ApiModelProperty("商品sku")
        @Excel(name = "商品sku")
        private String commoditySku;

        //1 新增 2存量
        private Integer isAdd;

        @ApiModelProperty("标题")
        @Excel(name = "标题")
        private String title;

        @ApiModelProperty("货号")
        @Excel(name = "货号")
        private String commodityNo;

        @ApiModelProperty("原货号")
        private String rawCommodityNo;

        @ApiModelProperty("品牌")
        @Excel(name = "品牌")
        private String brand;

        @ApiModelProperty("地址")
        @Excel(name = "地址")
        private String detailUrl;

        @ApiModelProperty("尺码")
        @Excel(name = "尺码")
        private String commoditySize;

        @ApiModelProperty("原尺码")
        private String rawCommoditySize;

        @ApiModelProperty("颜色")
        @Excel(name = "颜色")
        private String color;

        @ApiModelProperty("售价")
        @Excel(name = "售价")
        private BigDecimal productCurrentPrice;

        @ApiModelProperty("市场价")
        @Excel(name = "市场价")
        private BigDecimal productOriginalPrice;

        @ApiModelProperty("plus价格")
        @Excel(name = "plus价格")
        private BigDecimal employeePrice;

        @ApiModelProperty("库存")
        @Excel(name = "库存")
        private Integer stock;

        @ApiModelProperty("活动名称")
        @Excel(name = "活动名称")
        private String activeName;

        @Excel(name = "活动有效期")
        @ApiModelProperty("活动有效期")
        private String activeRange;

        @ApiModelProperty("是否可用优惠券")
        @Excel(name = "use_coupon")
        private String useCoupon;

        @ApiModelProperty("更新时间")
        @Excel(name = "更新时间")
        private Date updateTime;

        @ApiModelProperty(name = "批次号")
        private String batch;

        private String productImages; // 图片

        private String detailImages; // 详情图片

        private String detailDesc; // 详情描述
    }

    @Data
    @ApiModel("导入数据")
    class ImportAll {

        @ApiModelProperty("adidas")
        private List<AdidasImport> adidasImports;

        @ApiModelProperty("nike")
        private List<NikeImport> nikeImports;

        @ApiModelProperty("优购")
        private List<YouGouImport> youGouImports;

        @ApiModelProperty("采购平台")
        private String supplierName;
        @ApiModelProperty("id")
        private Long logId;
    }

    @Data
    @ApiModel("导入sku")
    class ImportSkuList {

        @Excel(name = "货号")
        @ApiModelProperty("货号")
        private String commodityNo;

        @Excel(name = "尺码")
        @ApiModelProperty("尺码")
        private String commoditySize;

        @Excel(name = "原价")
        @ApiModelProperty("商品牌价")
        private BigDecimal commodityPrice;

        @Excel(name = "品牌")
        @ApiModelProperty("品牌")
        private String commodityBrand;

        @Excel(name = "折扣")
        @ApiModelProperty("折扣")
        private BigDecimal discount;

    }

    @Data
    @ApiModel("查询vo")
    class CommodityList {

        @ApiModelProperty("id")
        private Integer id;

        @ApiModelProperty(name = "详情页标题")
        private String title;

        @ApiModelProperty("货号")
        private String commodityNo;

        @ApiModelProperty("颜色")
        private String color;

        @ApiModelProperty("尺码")
        private String commoditySize;

        @ApiModelProperty("尺码2")
        private String commoditySize2;

        private String tSkuId;

        @ApiModelProperty("skuId")
        private Long skuId;

        private String commoditySku;

        @ApiModelProperty("pSkuId")
        private String pSkuId;

        @ApiModelProperty(name = "商品条码")
        private String skuNo;

        @ApiModelProperty("当前价格")
        private BigDecimal productCurrentPrice;

        @ApiModelProperty("市场价")
        private BigDecimal productOriginalPrice;

        @ApiModelProperty("plus价格")
        private BigDecimal employeePrice;

        @ApiModelProperty(name = "价格")
        private BigDecimal price;

        @ApiModelProperty(name = "价格2")
        private BigDecimal price2;

        @ApiModelProperty(name = "1新增2存量")
        private Integer isAdd;
        @ApiModelProperty(name = "1未下架 2已下架")
        private Integer isDown;

        @ApiModelProperty("库存")
        private Integer stock;

        @ApiModelProperty("地址")
        private String detailUrl;

        @ApiModelProperty("活动名称")
        private String activeName;

        @ApiModelProperty("活动有效期")
        private String activeRange;

        @ApiModelProperty("是否可用优惠券")
        private String useCoupon;

        @ApiModelProperty("是否限购")
        private String buyLimit;

        @ApiModelProperty(name = "供货商平台")
        private String supplierName;
        @ApiModelProperty("活动优惠信息")
        private String promotions;

        @ApiModelProperty("活动优惠信息")
        private String promotion;

        /** 销售价 */
        @ApiModelProperty(name = "销售价")
        private BigDecimal sellingPrice;
        /**
         * 销售推销信息
         */
        @Excel(name = "销售推销信息")
        private String promotionMessage;

        @ApiModelProperty(name = "是否系数计算 0未计算1已计算")
        private String isCal;

        @ApiModelProperty(name = "计算模板id")
        private Long templateId;

        @ApiModelProperty(name = "是否上架 0未上架1已上架")
        private String isPut;

        @ApiModelProperty(name = "上架库存ID")
        private Long invenId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "有效期开始")
        private Date effectiveStarttime;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "有效期结束")
        private Date effectiveEndtime;

        @ApiModelProperty(name = "上架系数")
        private BigDecimal ratio;

        @ApiModelProperty(name = "计算公式")
        private String formula;

        /**
         * 更新时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;
        /**
         * 是否临时条码 0 不是 1是
         */
        @ApiModelProperty(name = "是否临时条码 0 不是 1是")
        private String isTemBarcode;

        @ApiModelProperty("是否最新 0不是1是")
        private String isShow;

        @ApiModelProperty(name = "批次号")
        private String batch;

        private List<String> urls;

        private String shopTitle;

        @ApiModelProperty("1黑名单")
        private  Integer status;

        @ApiModelProperty(name = "员工折扣 是或不是")
        private String productDiscount;

        /**
         * 上架时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date listingTime;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date takedownTime;

        @ApiModelProperty(name = "自定义活动标签")
        private  String customTag;

        @ApiModelProperty(name = "商品品牌名")
        private String brank;

        private Integer maintain;

    }

    @Data
    @ApiModel("查询vo")
    class CommodityListTm {

        /** $column.columnComment */
        private Long id;

        private String supplierName;

        /** 渠道 */
        @Excel(name = "渠道")
        private String shopTitle;

        /** 原货号 */
        @Excel(name = "原货号")
        private String rawCommodityNo;

        /** 货号 */
        @Excel(name = "货号")
        private String commodityNo;

        /** 尺码1 */
        @Excel(name = "尺码1")
        private String commoditySize;

        /** 尺码2 */
        @Excel(name = "尺码2")
        private String commoditySize2;

        /** 库存数量 */
        @Excel(name = "库存数量")
        private Long invenCnt;

        /** 价格 */
        @Excel(name = "价格")
        private BigDecimal price;

        /** 折扣 */
        @Excel(name = "折扣")
        private BigDecimal discount;

        /** 性别 */
        @Excel(name = "性别")
        private String sex;

        /** 季节 */
        @Excel(name = "季节")
        private String season;

        /** 品牌 */
        @Excel(name = "品牌")
        private String brand;

        /** 类目 */
        @Excel(name = "类目")
        private String category;

        /** 二级分类 */
        @Excel(name = "二级分类")
        private String secondaryClassification;

        /** 三级分类 */
        @Excel(name = "三级分类")
        private String threelevelClassification;

        /** out_sku */
        @Excel(name = "out_sku")
        private String outSku;

        private String sellId;

        /** 批次号 */
        private String batchId;

        private String productName;

        private List<String> urls;

        /**
         * 更新时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;

    }

    @Data
    @ApiModel("查询vo")
    class CommodityListBs {

        /** $column.columnComment */
        private Long id;

        private String supplierName;

        /** 渠道 */
        @Excel(name = "渠道")
        private String shopTitle;

        /** 仓位id */
        private Long whId;
        /** 仓位id */
        private List<Long> whIds;
        /** 仓位id */
        private String whName;

        @ExcelProperty(value = "商品条码")
        private String skuNo;

        /** 货号 */
        private String commodityNo;
        /** 原货号 */
        @ExcelProperty(value = "货号")
        private String rawCommodityNo;
        /** 原尺码 */
        @ExcelProperty(value = "尺码")
        private String rawCommoditySize;

        /** 尺码 */
        private String commoditySize;

        /** 品牌 */
        @ExcelProperty(value = "品牌")
        private String brand;

        /** 数量 */
        @ExcelProperty(value = "数量")
        private Long count;

        /** 价格 */
        @ExcelProperty(value = "吊牌价")
        private BigDecimal price;

        /** 价格 */
        @ExcelProperty(value = "成本价")
        private BigDecimal costPrice;

        /** 折扣 */
        @ExcelProperty(value = "折扣")
        private BigDecimal discount;
        /** 加点折扣 */
        private BigDecimal dotDiscount;

        /** 批次号 */
        private String batchId;

        private List<String> urls;

        /**
         * 更新时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;
    }

    @Data
    @ApiModel("查询vo")
    class StopeCommodityList {

        /**
         * sku_id
         */
        private Long skuId;

        /**
         * sku_id
         */
        private String pSkuId;

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
    }

    @Data
    @ApiModel("导入修改商品数据")
    class editImport {
        @Excel(name = "商品sku")
        private String commoditySku;

        @ApiModelProperty("货号")
        @Excel(name = "货号")
        private String commodityNo;

        @Excel(name = "尺码")
        private String commoditySize;

        @Excel(name = "品牌")
        private String brand;

    }

}
