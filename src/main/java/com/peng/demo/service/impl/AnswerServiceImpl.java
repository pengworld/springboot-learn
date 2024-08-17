package com.peng.demo.service.impl;

import com.peng.demo.dao.AnswerMapper;
import com.peng.demo.domain.entity.Answer;
import com.peng.demo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public List<Answer> findAll() {
        return answerMapper.findAll();
    }
}
