package com.ybs.paulsonmall.product.vo;

import lombok.Data;

/**
 * @ClassName: AttrVo
 * @Author Paulson
 * @Date 2020/6/27
 * @Description:
 */

@Data
public class AttrRespVo extends AttrVo {
    private String catelogName;
    private String groupName;
    private Long[] catelogPath;
}
