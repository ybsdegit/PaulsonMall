package com.ybs.paulsonmall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: Catelog2Vo
 * @Author Paulson
 * @Date 2020/7/20
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog2Vo {
    private String catalog1Id;
    private List<Catalog3Vo> catalog3List;
    private String id;
    private String name;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Catalog3Vo {
        private String catalog2Id;
        private String id;
        private String name;
    }
}
