package com.peng.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.demo.domain.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}
