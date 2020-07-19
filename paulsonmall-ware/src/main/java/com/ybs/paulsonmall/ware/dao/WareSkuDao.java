package com.ybs.paulsonmall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybs.paulsonmall.ware.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 *
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 17:16:55
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    Long getSkuStock(Long skuId);
}
