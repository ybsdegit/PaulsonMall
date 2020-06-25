package com.ybs.paulsonmall.member.feign;

import com.ybs.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: CouponFeignService
 * @Author Paulson
 * @Date 2020/6/25
 * @Description:
 */

@FeignClient("paulsonmall-coupon")
public interface CouponFeignService {

    @GetMapping("/coupon/coupon/member/list")
    public R membercoupons();
}
