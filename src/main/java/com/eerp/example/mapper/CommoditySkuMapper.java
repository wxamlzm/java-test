package com.eerp.example.mapper;


import com.eerp.example.domain.CommoditySku;
import com.eerp.example.model.DataTransferObject.commodityRequest;
import com.eerp.example.utils.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author xusz
 * @Description 商品条码Mapper接口
 * @Date 2020/10/27 13:59
 **/
public interface CommoditySkuMapper extends BaseMapper<CommoditySku> {
    /**
     * 根据ID查询商品条码信息
     *
     * @param skuId 商品条码ID
     * @return 商品条码
     */
    public CommoditySku selectCommoditySkuById(Long skuId);

    public List<CommoditySku> selectCommoditySkuByIds(long[] skuIds);

    /**
     * 根据sku查询商品条码信息
     * @param skuNo
     * @return
     */
    public CommoditySku selectCommoditySkuByNo(String skuNo);

    /**
     * 根据条码信息模糊查询商品条码列表
     *
     * @param CommoditySku 商品条码
     * @return 商品条码集合
     */
    public List<CommoditySku> selectCommoditySkuList(CommoditySku CommoditySku);

    /**
     * 新增商品条码
     *
     * @param CommoditySku 商品条码
     * @return 结果
     */
    public int insertCommoditySku(CommoditySku CommoditySku);

    /**
     * 修改商品条码
     *
     * @param CommoditySku 商品条码
     * @return 结果
     */
    public int updateCommoditySku(CommoditySku CommoditySku);

    /**
     * 根据货号、尺码查询商品信息
     * @param commodityNo
     * @param commoditySize
     * @return
     */
    public List<CommoditySku> selectByNoAndSize(@Param("commodityNo") String commodityNo, @Param("commoditySize") String commoditySize);


    int deleteCommoditySku(Long skuId);

    List<CommoditySku> selectCommodityNoList(@Param("commodityNos") List<String> commodityNos);

    List<CommoditySku> selectListCommodity(@Param("list") List<commodityRequest.QueryCommodity> queryNikeCommodities);

    List<CommoditySku> getBatchGoodsNoOrSize(List<commodityRequest.BatchCommodity> batchCommodities);

    List<CommoditySku> selectBySkuNos(@Param("list") List<String> skuNos);

    void batchUpdateCommodity(List<CommoditySku> commoditySkuses);

    List<String> getTitles(String commodityNo);

    List<CommoditySku> selectCommoditySkuByCommodityNo(@Param("commodityNo") String commodityNo);

    void batchUpdateWeight(@Param("skuIds") List<Long> skuId, @Param("weight") BigDecimal weight);

    void insertByBatch(List<CommoditySku> commoditySkus);

    void updateCommoditySkuWeight(CommoditySku sku);
}
