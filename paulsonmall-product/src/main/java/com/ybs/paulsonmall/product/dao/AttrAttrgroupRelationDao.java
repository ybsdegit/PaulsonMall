package com.ybs.paulsonmall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybs.paulsonmall.product.entity.AttrAttrgroupRelationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 01:09:30
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBatchRelation(List<AttrAttrgroupRelationEntity> entities);

}
