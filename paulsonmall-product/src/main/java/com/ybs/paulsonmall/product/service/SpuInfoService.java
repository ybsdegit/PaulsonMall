package com.ybs.paulsonmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybs.common.utils.PageUtils;
import com.ybs.paulsonmall.product.entity.SpuInfoEntity;
import com.ybs.paulsonmall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 01:09:30
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);
}

