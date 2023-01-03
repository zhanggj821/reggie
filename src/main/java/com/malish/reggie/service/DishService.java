package com.malish.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.malish.reggie.dto.DishDto;
import com.malish.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    //增加菜品，同时插入菜品口味信息
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
