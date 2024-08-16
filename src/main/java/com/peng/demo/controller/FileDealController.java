package com.peng.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.List;

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


    //文件读取到内存中 响应输出流 输出到前端
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
    public void downloadNet(String netAddress, String path) throws IOException {

    }


    @RequestMapping(value = "/testUploadFile", method = RequestMethod.POST)
    public void testUploadFile(HttpServletRequest req, MultipartHttpServletRequest multiReq) {
        // 获取上传文件的路径
        String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
        // 截取上传文件的文件名
        String uploadFileName = uploadFilePath.substring(
                uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
        // 截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(
                uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fis = (FileInputStream) multiReq.getFile("file1").getInputStream();
            fos = new FileOutputStream(new File(".//uploadFiles//" + uploadFileName
                    + ".")
                    + uploadFileSuffix);
            byte[] temp = new byte[1024];
            int i = fis.read(temp);
            while (i != -1) {
                fos.write(temp, 0, temp.length);
                fos.flush();
                i = fis.read(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @RequestMapping(value = "testUploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public void handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String uploadFilePath = file.getOriginalFilename();
                    // 截取上传文件的文件名
                    String uploadFileName = uploadFilePath
                            .substring(uploadFilePath.lastIndexOf('\\') + 1,
                                    uploadFilePath.indexOf('.'));
                    // 截取上传文件的后缀
                    String uploadFileSuffix = uploadFilePath.substring(
                            uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
                    stream = new BufferedOutputStream(new FileOutputStream(new File(
                            ".//uploadFiles//" + uploadFileName + "." + uploadFileSuffix)));
                    byte[] bytes = file.getBytes();
                    stream.write(bytes, 0, bytes.length);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (stream != null) {
                            stream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                //文件为空
                System.out.println("上传文件为空");
            }
        }
        System.out.println("文件接受成功了");
    }

    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res, String fileName) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("d://"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }
}
