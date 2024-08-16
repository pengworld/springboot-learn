package com.peng.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.SocketException;
import java.net.URLEncoder;

@RequestMapping("/file")
@RestController
public class FileDealController {

    @PostMapping(value = "upload")
    public String upload(MultipartFile file) throws SocketException, IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //在file文件夹中创建名为fileName的文件
        OutputStreamWriter op = new OutputStreamWriter(new FileOutputStream("./file/" + fileName), "UTF-8");
        //获取文件输入流
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
        char[] bytes = new char[12];

        while (inputStreamReader.read(bytes) != -1) {
            op.write(bytes);
        }

        //关闭输出流
        op.close();
        //关闭输入流
        inputStreamReader.close();
        return "上传成功";
    }


    //响应输出流 输出到前端
    @RequestMapping("/download")
    public void download(String path, HttpServletResponse response) {
        try {
            File file = new File(path);
            //文件名
            String filename = file.getName();
            //文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

            //将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/download1")
    public void download1(String path, HttpServletResponse response) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        response.reset();
        response.setContentType("application/octet-stream");
        String filename = new File(path).getName();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        inputStream.close();
    }

    //下载网络文件到本地
    @RequestMapping("/downloadNet")
    public void downloadNet(String netAddress, String path) throws IOException{

    }
}
