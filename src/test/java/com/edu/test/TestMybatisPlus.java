package com.edu.test;

import com.edu.bean.Admin;
import com.edu.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration("classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMybatisPlus {

    @Autowired
    private AdminService adminService;
    @Test
    public void testSelectById(){
        Admin admin = adminService.selectById(1);
        System.out.println(admin);
    }


}
