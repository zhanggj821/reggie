package com.malish.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.malish.reggie.common.CustomException;
import com.malish.reggie.entity.Category;
import com.malish.reggie.entity.Dish;
import com.malish.reggie.entity.Setmeal;
import com.malish.reggie.mapper.CategoryMapper;
import com.malish.reggie.service.CategoryService;
import com.malish.reggie.service.DishService;
import com.malish.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = (int) dishService.count(dishLambdaQueryWrapper);
        if(count1 > 0) {
            throw new CustomException("该类别有关联菜品，删除失败");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = (int) setmealService.count(setmealLambdaQueryWrapper);
        if(count2 > 0) {
            throw new CustomException("该类别有关联套餐，删除失败");
        }

        super.removeById(id);

    }
}
