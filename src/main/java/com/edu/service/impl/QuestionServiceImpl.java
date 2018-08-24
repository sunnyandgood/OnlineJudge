package com.edu.service.impl;

import com.edu.bean.Question;
import com.edu.mapper.QuestionMapper;
import com.edu.service.QuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
