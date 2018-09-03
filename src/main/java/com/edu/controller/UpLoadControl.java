package com.edu.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UpLoadControl {
    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request){
        //得到上传路径的硬盘路径
        String dir = request.getServletContext().getRealPath("/resources/upload");
        //得到文件名
        String fileName = file.getOriginalFilename();
        //获得文件对象
        File newFile = new File(dir,fileName);

        //存文件
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/resources/upload/"+fileName;
    }
}
