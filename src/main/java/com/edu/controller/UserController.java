package com.edu.controller;


import com.edu.bean.User;
import com.edu.service.UserService;
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
}

