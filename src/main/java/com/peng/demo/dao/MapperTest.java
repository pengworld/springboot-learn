package com.peng.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peng.demo.DemoApplication;
import com.peng.demo.domain.entity.Answer;
import com.peng.demo.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testMenuMapper() {
        List<String> list = menuMapper.selectPermsByUserId(1L);
        for (String str : list) {
            System.out.println(str);
        }
    }

    @Test
    public void testAnswerMapper() {
        List<Answer> list = answerMapper.findAll();
        for (Answer answer : list) {
            System.out.println(answer.getId() + "==" + answer.getSelections());
        }
    }
}

