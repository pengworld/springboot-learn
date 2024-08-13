package com.peng.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.demo.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
