package com.edu.controller;


import com.edu.bean.User;
import com.edu.service.UserService;
import com.edu.util.ExcelUtil;
import com.edu.util.R;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listAll")
    public R listAll(Integer pageNumber, Integer pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<User> users = userService.selectList(null);
        return R.ok().put("total",((Page)users).getTotal()).put("rows",users);
    }

    //按id查询，以供更改填值
    @GetMapping("/{userId}")
    public R get(@PathVariable("userId") Integer userId){
        User user = userService.selectById(userId);
        return R.ok().put("user",user);
    }

    @PostMapping("/update")
    public R update(User user){
        boolean update = userService.updateById(user);
        if (update){
            return R.ok("修改成功");
        }else {
            return R.error("修改失败");
        }
    }

    @PostMapping("/insert")
    public R insert(User user){
        boolean insert = userService.insert(user);
        if(insert){
            return R.ok("添加成功！");
        }else {
            return R.error("添加失败！");
        }
    }

    @PostMapping("/delete/{userId}")
    public R delete(@PathVariable("userId") Integer userId){
        boolean delete = userService.deleteById(userId);
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
        boolean deleteBatchIds = userService.deleteBatchIds(Arrays.asList(strings));
        if(deleteBatchIds){
            return R.ok("删除成功！");
        }else {
            return R.error("删除失败！");
        }
    }

    @PostMapping("/addToExcel")
    public R addToExcel() throws Exception {
        //获取数据
        List<User> list = userService.selectList(null);

        //excel标题
        String[] title = {"ID","用户名","姓名","密码"};

        //sheet文件名
        String sheetName = "用户";

        //将数据库中数据存到String数组中
        String[][] values = new String[list.size()][4];
        for(int i=0;i<list.size();i++) {
            if (list.get(i).getUserId()!=null){
                values[i][0] = list.get(i).getUserId().toString();
            }
            if (list.get(i).getNuserNumber()!=null){
                values[i][1] = list.get(i).getNuserNumber().toString();
            }
            values[i][2] = list.get(i).getUserName();
            values[i][3] = list.get(i).getUserPassword();

        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:/用户.xls");
        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook(sheetName, title, values);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        return R.ok("导出成功！");
    }

    @RequestMapping("/addFromExcel")
    public R addFromExcel(String userPath, HttpServletRequest request) {
        //得到上传路径的硬盘路径
        String dir = request.getServletContext().getRealPath("/");

        String path = dir + userPath;

        //读取文件
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
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
            User user = new User();
            if (values[i][0]!=null){
                user.setUserId(Integer.parseInt(values[i][0]));
            }
            if (values[i][1]!=null){
                user.setNuserNumber(Integer.parseInt(values[i][0]));
            }
            user.setUserName(values[i][2]);
            user.setUserPassword(values[i][3]);
            insert = userService.insertOrUpdateAllColumn(user);
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

