package com.ybs.paulsonmall.member.dao;

import com.ybs.paulsonmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 13:06:50
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
