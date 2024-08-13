package com.peng.demo.service;

import com.peng.demo.common.ResponseResult;
import com.peng.demo.domain.entity.User;

public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
