package com.malish.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.malish.reggie.dto.SetmealDto;
import com.malish.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    //新增套餐，同时保存套餐和菜品之间的关联关系
    public void saveWithDish(SetmealDto setmealDto);

    //删除套餐，同时删除与菜品的关联关系
    public void removeWithDish(List<Long> ids);
}
