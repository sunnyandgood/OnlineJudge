package com.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.edu.bean.Answer;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
public interface AnswerService extends IService<Answer> {
    List<Answer> selectAnswerUserQuestion();
    Answer selectAnswerByUserQuestion(Integer userId,Integer questionId);
}
