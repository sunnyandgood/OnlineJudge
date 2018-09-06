package com.edu.controller;


import com.edu.bean.Score;
import com.edu.service.ScoreService;
import com.edu.util.ExcelUtil;
import com.edu.util.R;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @PostMapping("/addToExcel")
    public R addToExcel() throws Exception {
        //获取数据
        List<Score> list = scoreService.selectScoreUserQuestion();

        //excel标题
        String[] title = {"用户名","姓名","试题单元","试题难度","题目","分数"};

        //sheet文件名
        String sheetName = "成绩";

        //将数据库中数据存到String数组中
        String[][] values = new String[list.size()][6];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<list.size();i++) {
            if (list.get(i).getUser().getNuserNumber()!=null){
                values[i][0] = list.get(i).getUser().getNuserNumber().toString();
            }
            values[i][1] = list.get(i).getUser().getUserName();
            values[i][2] = list.get(i).getQuestion().getQuestionChapter();
            if (list.get(i).getQuestion().getQuestionDifficult()!=null){
                values[i][3] = list.get(i).getQuestion().getQuestionDifficult().toString();
            }
            values[i][4] = list.get(i).getQuestion().getQuestionTitle();
            if(list.get(i).getScoreDegree()!=null){
                values[i][5] = list.get(i).getScoreDegree().toString();
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:/成绩.xls");
        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook(sheetName, title, values);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        return R.ok("导出成功！");
    }
}

