package com.malish.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.malish.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}