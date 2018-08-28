package com.edu.service.impl;

import com.edu.bean.Score;
import com.edu.mapper.ScoreMapper;
import com.edu.service.ScoreService;
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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Override
    public List<Score> selectScoreUserQuestion() {
        List<Score> scoreList = baseMapper.selectScoreUserQuestion();
        return scoreList;
    }
}
