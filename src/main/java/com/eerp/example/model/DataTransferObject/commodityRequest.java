package com.eerp.example.model.DataTransferObject;

import com.alibaba.fastjson.JSONObject;
import com.eerp.example.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface commodityRequest {
    @Data
    @ApiModel("查询qo")
    class QueryCommodity {

        @ApiModelProperty("货号")
        private String commodityNo;

        @ApiModelProperty("颜色")
        private String color;

        @ApiModelProperty("尺码")
        private String commoditySize;

        @ApiModelProperty("尺码2")
        private String commoditySize2;

        @ApiModelProperty(name = "供货商平台")
        private String supplierName;

        @ApiModelProperty(name = "是否系数计算 0未计算1已计算")
        private String isCal;

        @ApiModelProperty(name = "是否上架 0未上架1已上架")
        private String isPut;

        @ApiModelProperty(name = "1新增2存量")
        private Integer isAdd;

        @ApiModelProperty("是否可用优惠券")
        private String useCoupon;

        @ApiModelProperty("是否限购")
        private String buyLimit;

        @ApiModelProperty("活动信息")
        private String activeName;

        @ApiModelProperty("促销标签（Adidas）")
        private String promotionMessage;

        @ApiModelProperty("活动优惠信息")
        private String promotions;

        @ApiModelProperty("是否最新 0不是1是")
        private String isShow;

        /**是否临时条码 0 不是 1是*/
        @ApiModelProperty(name = "是否临时条码 0 不是 1是")
        private String  isTemBarcode;

        @ApiModelProperty(name = "店铺id")
        private String storeId;

        @ApiModelProperty(name = "开始时间")
        private String beginTime;

        @ApiModelProperty(name = "结束时间")
        private String endTime;

        @ApiModelProperty(name = "批次号")
        private String batch;

        @ApiModelProperty(name = "1未下架 2已下架")
        private Integer isDown;
    }

    @Data
    @ApiModel("查询qo")
    class BatchCommodity {
        @ApiModelProperty("平台")
        private String supplierName;

        @ApiModelProperty("店铺Id")
        private String storeId;

        @ApiModelProperty("货号")
        private String commodityNo;

        @ApiModelProperty("尺码")
        private String commoditySize;
    }

    @Data
    @ApiModel("查询qo")
    class ImportData {
        @ApiModelProperty("文件")
        private MultipartFile file;

        @ApiModelProperty("平台")
        private Integer supplierId;
    }


    @Data
    @ApiModel("手动计算系数")
    class ManualCal {

        @ApiModelProperty("ids")
        private Long[] ids;

        /**
         * 采购平台（供货商）
         */
        @Excel(name = "采购平台", readConverterExp = "供=货商")
        private String supplierName;

        /**
         * 是否可用优惠券 0 无 1优惠劵
         */
        @Excel(name = "是否可用优惠券 0 无 1优惠劵")
        private String useCoupon;

        /**
         * 类型 0 折扣 1满减
         */
        @Excel(name = "类型 0 折扣 1满减")
        private String type;

        /**
         * 折扣
         */
        @Excel(name = "折扣")
        private BigDecimal discount;

        /**
         * 优惠劵满金额（满）
         */
        @Excel(name = "优惠劵满金额", readConverterExp = "满=")
        private BigDecimal amount;

        /**
         * 减少金额（减）
         */
        @Excel(name = "减少金额", readConverterExp = "减=")
        private BigDecimal lessenAmount;

        /**
         * 有效期开始
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @Excel(name = "有效期开始", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
        private Date effectiveStarttime;

        /**
         * 有效期结束
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @Excel(name = "有效期结束", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
        private Date effectiveEndtime;

    }

    @Data
    @ApiModel("对接采集返回数据")
    class ReturnData{
        private String results;

        private Boolean success;

        private String msg;

        private String message;
    }

    @Data
    @ApiModel("对接采集返回数据酷锐")
    class TaoBaoData{
        private Long code;
        private JSONObject data;
    }


    @Data
    @ApiModel("对接采集返回数据酷锐")
    class TianMaData{
        private Long code;
        private String data;
        private String storeName;
        private String taskId;
    }

    @Data
    @ApiModel("商品维护采集返回数据")
    class dataList{
        private String brand; // 品牌
        private String commodityNo; // 货号
        private String ttm; // 上市时间
        private BigDecimal commodityPrice; // 牌价
        private String series; // 系列
        private String sex; // 性别
        private String sizeTable;
        @ApiModelProperty(name = "尺码1")
        private String commoditySize;
        @ApiModelProperty(name = "尺码2")
        private String commoditySize2;
        @ApiModelProperty(name = "尺码3")
        private String commoditySize3;
        private String productType; // 类别
        private String category; // 类目
        private String color; // 颜色
    }


    @Data
    @ApiModel("对接采集返回数据得物")
    class dataListDW{
        private String goods_code; // 货号
        private String ali_code;
        private String title;
        private String EU;
        private String US;
        private String UK;
        private String cloth;
        private String infos;
        private String categoryName;
        private String sell_num;
        private String size_info;
        private String img_url;
        @ApiModelProperty(name = "尺码1")
        private String commoditySize;
        @ApiModelProperty(name = "尺码2")
        private String commoditySize2;
        @ApiModelProperty(name = "尺码3")
        private String commoditySize3;
        @ApiModelProperty(name = "尺码4")
        private String commoditySize4;
    }

    @Data
    @ApiModel("修改商品数据")
    class buyerCommodity {
        @Excel(name = "店铺")
        private String shopTitle;

        @Excel(name = "id")
        private Integer id;

        private List<Integer> ids;

        private String tSkuId;

        private List<String> tSkuIds;

        /**
         * 品牌
         */
        @Excel(name = "品牌")
        private String brand;

        @Excel(name = "货号")
        private String commodityNo;

        @Excel(name = "尺码")
        private String commoditySize;

        /** 数量 */
        @Excel(name = "数量")
        private Integer stock;

        /** 牌价 */
        @Excel(name = "牌价")
        private BigDecimal productOriginalPrice;

        /**
         * 上架系数
         */
        @Excel(name = "上架系数")
        private BigDecimal ratio;

        /**
         * 有效期开始
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @Excel(name = "有效期开始", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
        private Date effectiveStarttime;

        /**
         * 有效期结束
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @Excel(name = "有效期结束", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
        private Date effectiveEndtime;
    }

    @Data
    @ApiModel("单款下架条件")
    class singleDelist {
        private String tSkuId;
        @ApiModelProperty(name = "采购平台")
        private String shopTitle;
        private Integer id;
    }

    @Data
    @ApiModel("一键下架条件")
    class taoBaoSkuUpdate {
        @Excel(name = "店铺sellId")
        private String storeId;
        @ApiModelProperty(name = "采购平台")
        private String supplierName;
    }
}
