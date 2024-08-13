package com.peng.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.demo.domain.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}
