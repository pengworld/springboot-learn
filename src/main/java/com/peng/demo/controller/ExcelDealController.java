package com.peng.demo.controller;

import com.peng.demo.domain.entity.Answer;
import com.peng.demo.service.AnswerService;
import com.peng.demo.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/excel")
public class ExcelDealController {

    @Autowired
    private AnswerService answerService;

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


    //excel导出
    @GetMapping("/downloadExcel")
    @Test
    public void downloadExcel(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个excel表单  sheet名称为 课调答卷表
        HSSFSheet sheet = workbook.createSheet("课调答卷表");
        //创建表头
        setTitle(workbook, sheet);

        List<Answer> answers = answerService.findAll();

        //从第二行开始  新增数据行  设置单元格数据
        int rowNum = 1;
        for (Answer answer : answers) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(answer.getId());
            row.createCell(1).setCellValue(answer.getSelections());
            row.createCell(2).setCellValue(answer.getChecks());
            row.createCell(3).setCellValue(answer.getContent());
            rowNum++;
        }

        String fileName = "survey-answer.xlsx";

        //将excel写入到输出流
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        workbook.write(os);
        os.flush();
        os.close();

        //将excel写到本地文件
        String path = "C:/Users/Administrator/Desktop/code/springboot-learn/file";
        FileOutputStream fos = null;
        fos = new FileOutputStream(path + fileName);
        workbook.write(fos);
    }

    //设置表头字段
    private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);  //第1行

        //设置列宽
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 100 * 256);

        //设置样式居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("单选题");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("多选题");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("简答题");
        cell.setCellStyle(style);
    }

    //excel导入
    @PostMapping("/uploadExcel")
    public String uploadExcel(MultipartFile file) {
        if (file == null) {
            return "file不能为空";
        }
        List<Answer> answers = new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            //如果多sheet页
            int sheetNum = workbook.getNumberOfSheets();
            //依次遍历每个sheet页
            for (int i = 0; i < sheetNum; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                //获取总行数
                int rows = sheet.getPhysicalNumberOfRows();
                //第一行是标题  从第二行开始 依次遍历每一行数据
                Answer answer = null;
                for (int j = 1; j < rows; j++) {
                    answer = new Answer();
                    HSSFRow row = sheet.getRow(j);
                    answer.setId(Long.parseLong(row.getCell(0).getStringCellValue())); //如果序号是自增主键 不用赋值
                    answer.setSelections(row.getCell(1).getStringCellValue());
                    answer.setChecks(row.getCell(2).getStringCellValue());
                    answer.setContent(row.getCell(3).getStringCellValue());
                    answers.add(answer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "导入成功";
    }

}
