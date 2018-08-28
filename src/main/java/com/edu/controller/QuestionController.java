package com.edu.controller;


import com.edu.bean.Question;
import com.edu.service.QuestionService;
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
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/listAll")
    public R listAll(Integer pageNumber, Integer pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<Question> questions = questionService.selectList(null);
        return R.ok().put("total",((Page)questions).getTotal()).put("rows",questions);
    }

    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id){
        boolean delete = questionService.deleteById(id);
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
        boolean deleteBatchIds = questionService.deleteBatchIds(Arrays.asList(strings));
        if(deleteBatchIds){
            return R.ok("删除成功！");
        }else {
            return R.error("删除失败！");
        }
    }

    //按id查询，以供更改填值
    @GetMapping("/{questionId}")
    public R get(@PathVariable("questionId") Integer questionId){
        Question question = questionService.selectById(questionId);
        return R.ok().put("question",question);
    }

    @PostMapping("/update")
    public R update(Question question){
        boolean update = questionService.updateById(question);
        if (update){
            return R.ok("修改成功");
        }else {
            return R.error("修改失败");
        }
    }

    @PostMapping("/insert")
    public R insert(Question question){
        boolean insert = questionService.insert(question);
        if(insert){
            return R.ok("添加成功！");
        }else {
            return R.error("添加失败！");
        }
    }

    @GetMapping("/list")
    public R list(){
        List<Question> questionList = questionService.selectList(null);
        return R.ok().put("questionList",questionList);
    }
}

