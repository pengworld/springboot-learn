package com.peng.demo.controller;

import com.peng.demo.util.ExcelUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/excel")
public class ExcelDealController {

    @RequestMapping("/upload")
    @ResponseBody
    public List<Object> excelUpload(@RequestParam("file") MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        //判断是否为.xls 或者 .xlsx的excel文件
        if (!name.substring(name.length() - 4).equals(".xls") || !name.substring(name.length() - 5).equals(".xlsx")) {
            List<Object> list = new ArrayList<>();
            list.add("excel文件格式错误");
            return list;
        }

        List<Object> list = ExcelUtil.excelToList(file.getInputStream());
        return list;
    }

}
