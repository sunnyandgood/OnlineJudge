package com.edu.service;

import com.edu.bean.Score;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
public interface ScoreService extends IService<Score> {
    List<Score> selectScoreUserQuestion();
}
