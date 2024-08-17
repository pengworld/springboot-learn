package com.peng.demo.dao;

import com.peng.demo.domain.entity.Answer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerMapper {
    List<Answer> findAll();
}
