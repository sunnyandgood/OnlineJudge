package com.edu.controller;


import com.edu.bean.Admin;
import com.edu.service.AdminService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/listAll")
    public R listAll(Integer pageNumber, Integer pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<Admin> admins = adminService.selectList(null);
        return R.ok().put("total",((Page)admins).getTotal()).put("rows",admins);
    }

    //按id查询，以供更改填值
    @GetMapping("/{adminId}")
    public R get(@PathVariable("adminId") Integer adminId){
        Admin admin = adminService.selectById(adminId);
        return R.ok().put("admin",admin);
    }

    @PostMapping("/update")
    public R update(Admin admin){
        boolean update = adminService.updateById(admin);
        if (update){
            return R.ok("修改成功");
        }else {
            return R.error("修改失败");
        }
    }

    @PostMapping("/insert")
    public R insert(Admin admin){
        boolean insert = adminService.insert(admin);
        if(insert){
            return R.ok("添加成功！");
        }else {
            return R.error("添加失败！");
        }
    }

    @PostMapping("/delete/{adminId}")
    public R delete(@PathVariable("adminId") Integer adminId){
        boolean delete = adminService.deleteById(adminId);
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
        boolean deleteBatchIds = adminService.deleteBatchIds(Arrays.asList(strings));
        if(deleteBatchIds){
            return R.ok("删除成功！");
        }else {
            return R.error("删除失败！");
        }
    }

    @PostMapping("/addToExcel")
    public R addToExcel() throws Exception {
        //获取数据
        List<Admin> list = adminService.selectList(null);

        //excel标题
        String[] title = {"ID","用户名","密码"};

        //sheet文件名
        String sheetName = "管理员";

        //将数据库中数据存到String数组中
        String[][] values = new String[list.size()][3];
        for(int i=0;i<list.size();i++) {
            if (list.get(i).getAdminId()!=null){
                values[i][0] = list.get(i).getAdminId().toString();
            }
            values[i][1] = list.get(i).getAdminName();
            values[i][2] = list.get(i).getAdminPassword();
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:/管理员.xls");
        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook(sheetName, title, values);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        return R.ok("导出成功！");
    }

    @RequestMapping("/addFromExcel")
    public R addFromExcel(String adminPath, HttpServletRequest request) {
        //得到上传路径的硬盘路径
        String dir = request.getServletContext().getRealPath("/");

        String path = dir + adminPath;

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
            Admin admin = new Admin();
            if (values[i][0]!=null){
                admin.setAdminId(Integer.parseInt(values[i][0]));
            }
            admin.setAdminName(values[i][1]);
            admin.setAdminPassword(values[i][2]);
            insert = adminService.insertOrUpdateAllColumn(admin);
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

