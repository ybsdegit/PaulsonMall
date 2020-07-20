package com.ybs.paulsonmall.product.web;

import com.ybs.paulsonmall.product.entity.CategoryEntity;
import com.ybs.paulsonmall.product.service.CategoryService;
import com.ybs.paulsonmall.product.vo.Catalog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndexController
 * @Author Paulson
 * @Date 2020/7/20
 * @Description:
 */

@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;


    @GetMapping({"/", "/index.html"})
    public String index(Model model) {
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        model.addAttribute("categorys", categoryEntities);
        return "index";
    }

    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catalog2Vo>> getCateLogJson() {
        Map<String, List<Catalog2Vo>> cateLogJson = categoryService.getCateLogJson();
        return cateLogJson;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
