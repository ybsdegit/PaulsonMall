package com.ybs.paulsonmall.product.feign;

import com.ybs.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName: WareFeignService
 * @Author Paulson
 * @Date 2020/7/19
 * @Description:
 */

@FeignClient("paulsonmall-ware")
public interface WareFeignService {


    @PostMapping("/ware/waresku/hasstock")
    public R getSkusHasStock(@RequestBody List<Long> skuIds);

}
