package com.edu.controller;


import com.edu.bean.Answer;
import com.edu.service.AnswerService;
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
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/listAll")
    public R listAll(Integer pageNumber, Integer pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<Answer> answers = answerService.selectAnswerUserQuestion();
        return R.ok().put("total",((Page)answers).getTotal()).put("rows",answers);
    }

    @PostMapping("/removeAll")
    public R removeAll(String ids){
        String[] strings = ids.split(",");
        System.out.println(strings);
        boolean deleteBatchIds = answerService.deleteBatchIds(Arrays.asList(strings));
        if(deleteBatchIds){
            return R.ok("删除成功！");
        }else {
            return R.error("删除失败！");
        }
    }

    @PostMapping("/delete/{answerId}")
    public R delete(@PathVariable("answerId") Integer answerId){
        boolean delete = answerService.deleteById(answerId);
        if(delete){
            //删除成功
            return R.ok("删除成功！");
        }else {
            //删除失败
            return R.error("删除失败！");
        }
    }
}

