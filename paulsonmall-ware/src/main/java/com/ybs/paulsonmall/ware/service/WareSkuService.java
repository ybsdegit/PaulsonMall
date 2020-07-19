package com.ybs.paulsonmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybs.common.utils.PageUtils;
import com.ybs.paulsonmall.ware.entity.WareSkuEntity;
import com.ybs.paulsonmall.ware.vo.SkuHasStockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 17:16:55
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkusHaaStock(List<Long> skuIds);
}

