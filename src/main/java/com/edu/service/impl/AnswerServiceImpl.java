package com.edu.service.impl;

import com.edu.bean.Answer;
import com.edu.mapper.AnswerMapper;
import com.edu.service.AnswerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Override
    public List<Answer> selectAnswerUserQuestion() {
        List<Answer> answers = baseMapper.selectAnswerUserQuestion();
        return answers;

    }

    @Override
    public List<Answer> selectAnswerByUserQuestion(Integer userId, Integer questionId) {
        List<Answer> answers = baseMapper.selectAnswerByUserQuestion(userId, questionId);
        return answers;
    }
}
