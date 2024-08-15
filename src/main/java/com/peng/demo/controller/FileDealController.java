package com.peng.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.SocketException;

@RequestMapping("/file")
@RestController
public class FileDealController {
    @PostMapping(value = "upload")
    public String upload(MultipartFile file) throws SocketException, IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //在file文件夹中创建名为fileName的文件
        OutputStreamWriter op = new OutputStreamWriter(new FileOutputStream("./file/"+fileName),"UTF-8");
        //获取文件输入流
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
        char[] bytes = new char[12];

        while(inputStreamReader.read(bytes) != -1){
            op.write(bytes);
        }

        //关闭输出流
        op.close();
        //关闭输入流
        inputStreamReader.close();
        return "上传成功";
    }
}
