package com.malish.reggie.dto;

import com.malish.reggie.entity.Setmeal;
import com.malish.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
