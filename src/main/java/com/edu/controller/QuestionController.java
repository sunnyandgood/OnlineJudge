package com.edu.controller;


import com.edu.bean.Question;
import com.edu.service.QuestionService;
import com.edu.util.ExcelUtil;
import com.edu.util.R;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
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

    @PostMapping("/addToExcel")
    public R addToExcel() throws Exception {
        //获取数据
        List<Question> list = questionService.selectList(null);

        //excel标题
        String[] title = {"题目id","试题单元","试题难度","题目","出题时间","出题者","参考答案","测试数据1","测试数据2","测试数据3"};

        //sheet文件名
        String sheetName = "题库";

        //将数据库中数据存到String数组中
        String[][] values = new String[list.size()][10];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<list.size();i++) {
            values[i][0] = list.get(i).getQuestionId().toString();
            values[i][1] = list.get(i).getQuestionChapter();
            values[i][2] = list.get(i).getQuestionDifficult().toString();
            values[i][3] = list.get(i).getQuestionTitle();
//            if(list.get(i).getQuestionTime()!=null) {
//                values[i][4] = list.get(i).getQuestionTime().toString();
//            }
            if(list.get(i).getQuestionTime()!=null) {
                values[i][4] = dateFormat.format(list.get(i).getQuestionTime());
            }
            values[i][5] = list.get(i).getQuestionAuthor();
            values[i][6] = list.get(i).getQuestionAnswer();
            values[i][7] = list.get(i).getQuestionPara1();
            values[i][8] = list.get(i).getQuestionPara2();
            values[i][9] = list.get(i).getQuestionPara3();
            System.out.println(list.get(i));
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:/题库.xls");
        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook(sheetName, title, values);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        return R.ok("导出成功！");
    }

//    @PostMapping("/addFromExcel")
//    public R addFromExcel(){
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //读取文件
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream("D:/qq.xlsx");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        //sheet文件名
//        String sheetName = "Sheet1";
//        //将excel中的数据读取到String数组中
//        String[][] values = new String[0][];
//
//        try {
//            values = ExcelUtil.getValuesFromExcel(inputStream,sheetName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //将String数组中的数据封装到实体类
//        boolean insert = false;
//        for(int i=1;i<values.length;i++) {
//            Question question = new Question();
//            if(values[i][0]!=null) {
//                question.setQuestionId(Integer.parseInt(values[i][0]));
//            }
//            question.setQuestionChapter(values[i][1]);
//            if(values[i][2]!=null) {
//                question.setQuestionDifficult(Integer.parseInt(values[i][2]));
//            }
//
//            question.setQuestionTitle(values[i][3]);
//
//            if(values[i][4]!=null && !(values[i][4].equals(""))) {
//                System.out.println(values[i][4] + " 00:00:00");
//                try {
//                    question.setQuestionTime(dateFormat.parse(values[i][4]+" 00:00:00"));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
////				question.setQuestionTime(values[i][4]);
//            }
//
//            question.setQuestionAuthor(values[i][5]);
//            question.setQuestionAnswer(values[i][6]);
//            question.setQuestionPara1(values[i][7]);
//            question.setQuestionPara2(values[i][8]);
//            question.setQuestionPara3(values[i][9]);
//            //导入数据库
////            insert = questionService.insert(question);
////            insert = questionService.insertAllColumn(question);
//            insert = questionService.insertOrUpdateAllColumn(question);
//        }
//
//
//        try {
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        if (insert){
//            return R.ok("导入成功！");
//        }else {
//            return R.error("导入失败！");
//        }
//    }


    @PostMapping("/addFromExcel")
    public R addFromExcel(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //读取文件
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:/qq.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //sheet文件名
        String sheetName = "Sheet1";
        //将excel中的数据读取到String数组中
        String[][] values = new String[0][];

        try {
            values = ExcelUtil.getValuesFromExcel(inputStream,sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将String数组中的数据封装到实体类
        boolean insert = false;
        for(int i=1;i<values.length;i++) {
            Question question = new Question();
            if(values[i][0]!=null) {
                question.setQuestionId(Integer.parseInt(values[i][0]));
            }
            question.setQuestionChapter(values[i][1]);
            if(values[i][2]!=null) {
                question.setQuestionDifficult(Integer.parseInt(values[i][2]));
            }

            question.setQuestionTitle(values[i][3]);

            if(values[i][4]!=null && !(values[i][4].equals(""))) {
                System.out.println(values[i][4] + " 00:00:00");
                try {
                    question.setQuestionTime(dateFormat.parse(values[i][4]+" 00:00:00"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//				question.setQuestionTime(values[i][4]);
            }

            question.setQuestionAuthor(values[i][5]);
            question.setQuestionAnswer(values[i][6]);
            question.setQuestionPara1(values[i][7]);
            question.setQuestionPara2(values[i][8]);
            question.setQuestionPara3(values[i][9]);
            //导入数据库
//            insert = questionService.insert(question);
//            insert = questionService.insertAllColumn(question);
            insert = questionService.insertOrUpdateAllColumn(question);
        }


        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (insert){
            return R.ok("导入成功！");
        }else {
            return R.error("导入失败！");
        }
    }
}

