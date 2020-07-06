package com.ybs.paulsonmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybs.common.utils.PageUtils;
import com.ybs.paulsonmall.product.entity.AttrEntity;
import com.ybs.paulsonmall.product.vo.AttrGroupRelationVo;
import com.ybs.paulsonmall.product.vo.AttrRespVo;
import com.ybs.paulsonmall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 01:09:30
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attrVo);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);
}

