package com.malish.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.malish.reggie.common.CustomException;
import com.malish.reggie.dto.SetmealDto;
import com.malish.reggie.entity.Setmeal;
import com.malish.reggie.entity.SetmealDish;
import com.malish.reggie.mapper.SetmealMapper;
import com.malish.reggie.service.SetmealDishService;
import com.malish.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存基本套餐信息
        this.save(setmealDto);

        Long id = setmealDto.getId();
        //保存关联关系
        List<SetmealDish> dishes = setmealDto.getSetmealDishes();
        dishes = dishes.stream().map((item) -> {
            item.setSetmealId(id);
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(dishes);
    }

    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //查询是否存在售卖中的套餐
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);
        long count = this.count(queryWrapper);

        if(count > 0) {
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        //可以删除，删除套餐数据
        this.removeBatchByIds(ids);

        //删除关系表数据
        LambdaQueryWrapper<SetmealDish> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(SetmealDish::getSetmealId, ids);

        setmealDishService.remove(queryWrapper1);
    }
}
