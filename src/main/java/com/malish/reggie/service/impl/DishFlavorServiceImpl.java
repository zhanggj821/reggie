package com.malish.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.malish.reggie.dto.DishDto;
import com.malish.reggie.entity.DishFlavor;
import com.malish.reggie.mapper.DishFlavorMapper;
import com.malish.reggie.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
