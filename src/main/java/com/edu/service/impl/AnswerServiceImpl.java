package com.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.edu.bean.Answer;
import com.edu.mapper.AnswerMapper;
import com.edu.service.AnswerService;
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
    public Answer selectAnswerByUserQuestion( Integer userId, Integer questionId) {
        Answer answer = baseMapper.selectAnswerByUserQuestion(userId, questionId);
        return answer;
    }
}
