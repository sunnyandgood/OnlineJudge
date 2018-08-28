package com.edu.controller;


import com.edu.bean.Score;
import com.edu.service.ScoreService;
import com.edu.util.R;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/listAll")
    public R listAll(Integer pageNumber, Integer pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<Score> scores = scoreService.selectScoreUserQuestion();
        return R.ok().put("total",((Page)scores).getTotal()).put("rows",scores);
    }

    //按id查询，以供更改填值
    @GetMapping("/{scoreId}")
    public R get(@PathVariable("scoreId") Integer scoreId){
        Score score = scoreService.selectById(scoreId);
        return R.ok().put("score",score);
    }

    @PostMapping("/update")
    public R update(Score score){
        boolean update = scoreService.updateById(score);
        if (update){
            return R.ok("修改成功");
        }else {
            return R.error("修改失败");
        }
    }

    @PostMapping("/delete/{scoreId}")
    public R delete(@PathVariable("scoreId") Integer scoreId){
        boolean delete = scoreService.deleteById(scoreId);
        if(delete){
            //删除成功
            return R.ok("删除成功！");
        }else {
            //删除失败
            return R.error("删除失败！");
        }
    }

    @PostMapping("/removeAll")
    public R removeAll(String ids){
        String[] strings = ids.split(",");
        System.out.println(strings);
        boolean deleteBatchIds = scoreService.deleteBatchIds(Arrays.asList(strings));
        if(deleteBatchIds){
            return R.ok("删除成功！");
        }else {
            return R.error("删除失败！");
        }
    }
}

