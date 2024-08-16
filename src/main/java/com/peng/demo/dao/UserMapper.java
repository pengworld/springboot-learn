package com.peng.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.demo.domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
