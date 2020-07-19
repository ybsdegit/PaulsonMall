package com.ybs.paulsonmall.product.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: IndexController
 * @Author Paulson
 * @Date 2020/7/20
 * @Description:
 */

@Controller
public class IndexController {


    @GetMapping({"/", "/index.html"})
    public String index() {
        return "index";
    }
}
