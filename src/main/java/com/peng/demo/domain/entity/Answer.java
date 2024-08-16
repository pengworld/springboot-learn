package com.peng.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 课调答卷表（answer）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "answer")
public class Answer implements Serializable {

    private static final Long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 单选题
     */
    private String selections;
    /**
     * 多选题
     */
    private String checks;
    /**
     * 简单题
     */
    private String content;
}
