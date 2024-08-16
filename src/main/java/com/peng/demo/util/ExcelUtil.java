package com.peng.demo.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static List<Object> excelToList(InputStream inputStream) {
        List<Object> list = new ArrayList<>();
        Workbook workbook = null;
        //根据输入流读取excel
        try {
            //excel 工作薄
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();

            //excel sheet   比如第一个excel sheet页
            Sheet sheet = workbook.getSheetAt(0);

            //行数
            int rowLen = sheet.getLastRowNum() - 1;

            //第1行   列数
            Row row = sheet.getRow(0);
            int ColLen = row.getLastCellNum();

            //根据excel 单元格具体操作    数据封装到list


        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return list;
    }
}
