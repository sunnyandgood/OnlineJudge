package com.edu.controller;


import com.edu.bean.Answer;
import com.edu.bean.User;
import com.edu.service.AnswerService;
import com.edu.util.ExcelUtil;
import com.edu.util.R;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @PostMapping("/addToExcel")
    public R addToExcel() throws Exception {
        //获取数据
        List<Answer> list = answerService.selectAnswerUserQuestion();

        //excel标题
        String[] title = {"用户名","姓名","试题单元","试题难度","题目","用户答案","答题时间"};

        //sheet文件名
        String sheetName = "答卷";

        //将数据库中数据存到String数组中
        String[][] values = new String[list.size()][7];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i=0;i<list.size();i++) {
            if (list.get(i).getUser().getNuserNumber()!=null){
                values[i][0] = list.get(i).getUser().getNuserNumber().toString();
            }
            values[i][1] = list.get(i).getUser().getUserName();
            values[i][2] = list.get(i).getQuestion().getQuestionChapter();
            if (list.get(i).getQuestion().getQuestionDifficult()!=null){
                values[i][3] = list.get(i).getQuestion().getQuestionDifficult().toString();
            }
            values[i][4] = list.get(i).getQuestion().getQuestionTitle();
            values[i][5] = list.get(i).getAnswerResult();
            if (list.get(i).getAnswerTime()!=null){
                values[i][6] = dateFormat.format(list.get(i).getAnswerTime());
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:/答卷.xls");
        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook(sheetName, title, values);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        return R.ok("导出成功！");
    }

    @GetMapping("/display/{questionId}")
    public R display(@PathVariable("questionId") Integer questionId, HttpServletRequest request){
        //获取session里的登录状态
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        Integer userId = user.getUserId();
        System.out.println("userId="+userId);
        System.out.println("questionId="+questionId);
        Answer answer = answerService.selectAnswerByUserQuestion(userId, questionId);
        System.out.println(answer);
        return R.ok().put("answer",answer);
    }

    @PostMapping("/insertOrUpdate/{questionId}")
    public R insertOrUpdate(@PathVariable("questionId") Integer questionId,HttpServletRequest request){
        //获取session里的登录状态
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Integer userId = user.getUserId();

//        String[] questionTitles = request.getParameterValues("questionTitle");
//        System.out.println("questionTitles[0]"+questionTitles[0]);

        String[] codes = request.getParameterValues("code1");
        System.out.println("codes[0]"+codes[0]);

//        Answer answer = new Answer();
//        answer.setUserId(userId);
//        answer.setQuestionId(questionId);
//        answer.setAnswerResult(codes[0]);
//        answer.setAnswerTime(new Date());
//
//        Answer answer1 = answerService.selectAnswerByUserQuestion(userId, questionId);
//        boolean insert = false;
//        if(answer1!=null){
//            answer.setAnswerId(answer1.getAnswerId());
//            insert = answerService.updateById(answer);
//        }else {
//            insert = answerService.insert(answer);
//        }
//
//        if(insert){
//            return R.ok("操作成功!");
//        }else {
//            return R.error("操作失败！");
//        }
        return R.ok();
    }
}

