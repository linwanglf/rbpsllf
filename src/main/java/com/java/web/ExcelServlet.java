package com.java.web;

import com.alibaba.fastjson.JSON;
import com.java.manager.ScoreManager;
import com.java.model.ExamScore;
import com.java.util.DateUtil;
import com.java.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/29 14:22
 * Description:输出Excel
 */
@Slf4j
public class ExcelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info(DateUtil.getCurrent() + " Start ExcelServlet  ");
        request.setCharacterEncoding("utf-8");
        ScoreManager scoreManager = new ScoreManager();

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();

        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("成绩表");

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);

        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("学员考试成绩一览表");
    //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("班级");
        row2.createCell(2).setCellValue("笔试成绩");
        row2.createCell(3).setCellValue("考试时间");

        List<ExamScore> list = scoreManager.getScoreList();
        ExamScore[] strArray  = new ExamScore[list.size()];
        list.toArray(strArray);
        for(int i=0; i<strArray.length; i++ ){
            HSSFRow row=sheet.createRow(i+2);
            row.createCell(0).setCellValue(strArray[i].getName());
            log.info("name{}",strArray[i].getName());
            row.createCell(1).setCellValue(strArray[i].getClassGrade());
            row.createCell(2).setCellValue(strArray[i].getScore());
            row.createCell(3).setCellValue(strArray[i].getExamTime());
        }

        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }
}
