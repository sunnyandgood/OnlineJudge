package com.edu.mapper;

import com.edu.bean.Answer;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
public interface AnswerMapper extends BaseMapper<Answer> {
    List<Answer> selectAnswerUserQuestion();
    Answer selectAnswerByUserQuestion(@Param("userId") Integer userId, @Param("questionId") Integer questionId);
}
