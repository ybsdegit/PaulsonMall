package com.ybs.paulsonmall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybs.common.utils.PageUtils;
import com.ybs.common.utils.Query;
import com.ybs.paulsonmall.product.dao.CategoryDao;
import com.ybs.paulsonmall.product.entity.CategoryEntity;
import com.ybs.paulsonmall.product.service.CategoryBrandRelationService;
import com.ybs.paulsonmall.product.service.CategoryService;
import com.ybs.paulsonmall.product.vo.Catalog2Vo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    // private Map<String, Object> cache = new HashMap<>();

    @Autowired
    RedissonClient redisson;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> entities = baseMapper.selectList(null);
        // 1级分类
        List<CategoryEntity> level1Menus = entities.stream()
                .filter(categoryEntity ->
                        categoryEntity.getParentCid() == 0)
                .map((menu) -> {
                    menu.setChildren(getChildrens(menu, entities));
                    return menu;
                })
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        // TODO 检查当前删除的菜单是否别引用
        // 逻辑删除

        // 物理删除
        baseMapper.deleteBatchIds(asList);

    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);
        return paths.toArray(new Long[parentPath.size()]);
    }

    @CacheEvict(value = "category", key = "'getLevel1Categorys'")
    @Transactional
    @Override
    public void updateDetail(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());

    }

    @Override
    @Cacheable(value = {"category"}, key = "#root.method.name")
    public List<CategoryEntity> getLevel1Categorys() {
        System.out.println("getLevel1Categorys.................");
        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCateLogJson() {
        /*
         * 1、共结果缓存：解决缓存穿透
         * 2、设置过期时间（加随机值）：解决缓存雪崩
         * 3、加锁：解决缓存击穿
         */
        Map<String, List<Catalog2Vo>> cateLogJsonFromDb = null;
        String catalogJson = redisTemplate.opsForValue().get("catalogJson");
        if (!StringUtils.isEmpty(catalogJson)) {
            cateLogJsonFromDb = JSON.parseObject(catalogJson, new TypeReference<Map<String, List<Catalog2Vo>>>() {
            });
            return cateLogJsonFromDb;
        }
        cateLogJsonFromDb = getCateLogJsonFromRedissonLock();
        redisTemplate.opsForValue().set("catalogJson", JSON.toJSONString(cateLogJsonFromDb), 1, TimeUnit.DAYS);
        return cateLogJsonFromDb;

    }


    public Map<String, List<Catalog2Vo>> getCateLogJsonFromRedissonLock() {
        RLock lock = redisson.getLock("catalogJson-lock");
        lock.lock();
        Map<String, List<Catalog2Vo>> cateLogJsonFromDb;
        try {
            cateLogJsonFromDb = getCateLogJsonFromDb();
        } finally {
            lock.unlock();
        }
        return cateLogJsonFromDb;
    }


    public Map<String, List<Catalog2Vo>> getCateLogJsonFromDb() {
        /*
         * 优化
         * 1、将数据库的多次查询变为一次
         * 2、缓存
         */

        /*Map<String, List<Catalog2Vo>> catalogJson = (Map<String, List<Catalog2Vo>>) cache.get("catalogJson");
        if (catalogJson != null) {
            return catalogJson;
        }*/
        List<CategoryEntity> selectList = baseMapper.selectList(null);
        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);
        Map<String, List<Catalog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 二级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
            List<Catalog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catalog2Vo catelog2Vo = new Catalog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    // 找三级分类
                    List<CategoryEntity> level3CateLog = getParent_cid(selectList, l2.getCatId());
                    if (level3CateLog != null) {
                        List<Catalog2Vo.Catalog3Vo> catelog3VoList = level3CateLog.stream().map(l3 -> {
                            Catalog2Vo.Catalog3Vo catelog3Vo = new Catalog2Vo.Catalog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(catelog3VoList);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));
        // cache.put("catalogJson", parent_cid);
        return parent_cid;
    }

    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList, Long parent_cid) {
        return selectList.stream().filter(item -> item.getParentCid() == parent_cid).collect(Collectors.toList());
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        paths.add(catelogId);
        CategoryEntity ce = this.getById(catelogId);
        if (ce.getParentCid() != 0) {
            findParentPath(ce.getParentCid(), paths);
        }
        return paths;
    }

    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId())).map(categoryEntity -> {
                    //1、找到子菜单
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                })
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
        return children;
    }

}