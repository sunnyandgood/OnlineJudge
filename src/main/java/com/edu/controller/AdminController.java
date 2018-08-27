package com.edu.controller;


import com.edu.bean.Admin;
import com.edu.service.AdminService;
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
}

