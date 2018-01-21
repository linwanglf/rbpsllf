package com.java.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.ExcelDao;
import com.java.manager.AttentanceManager;
import com.java.manager.ScoreManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.java.model.PageBean;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.java.model.Attentance;
import com.java.model.ExamScore;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/29 14:22
 * Description:
 */
@Slf4j
public class ExcelServlet extends HttpServlet {


    DbUtil dbUtil=new DbUtil();
    ExcelDao excelDao=new ExcelDao();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");
        String action=request.getParameter("action");
        if("exportex".equals(action)){
            try {
                this.exportexcelAction(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void setBorder(CellRangeAddress cellRangeAddress, Sheet sheet,
                          HSSFWorkbook wb) throws Exception {
        RegionUtil.setBorderLeft(1, cellRangeAddress, sheet, wb);
        RegionUtil.setBorderBottom(1, cellRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(1, cellRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(1, cellRangeAddress, sheet, wb);

    }

    public void setcellborder(String fonttype,int fontInt, Sheet sheet,
                          HSSFWorkbook wb,HSSFCell cell) throws Exception {


        HSSFFont font = wb.createFont();
        font.setFontName(fonttype);
        font.setFontHeightInPoints((short) fontInt);// 设置字体大小

        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //4、Set font into style
        style.setFont(font);

        //5、set boder设置边框线
//        style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
//        style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
//        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT);
//        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//
//        style.setTopBorderColor(IndexedColors.MAROON.getIndex());
//        style.setBottomBorderColor(IndexedColors.SKY_BLUE.getIndex());
//        style.setLeftBorderColor(IndexedColors.ORANGE.getIndex());
//        style.setRightBorderColor(IndexedColors.BLUE_GREY.getIndex());


        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());

        //6、set style to  a cell.
        cell.setCellStyle(style);//设置单元格边框
    }

    public void setcellborder_title(String fonttype,int fontInt, Sheet sheet,
                              HSSFWorkbook wb,HSSFCell cell) throws Exception {


        HSSFFont font = wb.createFont();
        font.setFontName(fonttype);
        font.setFontHeightInPoints((short) fontInt);// 设置字体大小

        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //4、Set font into style
        style.setFont(font);

        //5、set boder设置边框线
//        style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
//        style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
//        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT);
//        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//
//        style.setTopBorderColor(IndexedColors.MAROON.getIndex());
//        style.setBottomBorderColor(IndexedColors.SKY_BLUE.getIndex());
//        style.setLeftBorderColor(IndexedColors.ORANGE.getIndex());
//        style.setRightBorderColor(IndexedColors.BLUE_GREY.getIndex());


//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//
//        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//        style.setRightBorderColor(IndexedColors.BLACK.getIndex());

        //6、set style to  a cell.
        cell.setCellStyle(style);//设置单元格边框
    }


    private void exportexcelAction(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        //行row、列cell从0开始计算
//----------------------------表头操作设定-----------------------------------------------------
        // 1、Create a cell with a value
        int rownumset=1;
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();

        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("计划内");

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row2=sheet.createRow(rownumset);

        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row2.createCell(0);
        //设置单元格内容
        cell.setCellValue("动力总成本部员工加班月度计划表(2017年10月)");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(rownumset,rownumset,0,12));
        CellRangeAddress CellRangeAddress1 = new CellRangeAddress(rownumset,rownumset,0,12);
        sheet.addMergedRegion(CellRangeAddress1);

        setcellborder_title("宋体",20, sheet, wb,cell);//设置单元格大小，字体大小
        //setBorder(CellRangeAddress1, sheet, wb);//对于合并的单元格，此句必须放在其它属性设置完成之后执行


        ////wb.getSheet("计划内").getRow(0).getCell(0).setCellStyle(style);

        //7、设置默认列宽
        ////wb.getSheet("sheetname7").setDefaultColumnWidth(12);


        //8、设置列宽
        wb.getSheet("计划内").setColumnWidth(5, 5 * 956);
        wb.getSheet("计划内").setColumnWidth(10, 5 * 956);
        wb.getSheet("计划内").setColumnWidth(11, 5 * 956);
        wb.getSheet("计划内").setColumnWidth(12, 5 * 956);
//---------------------------------------------------------------------------------------
        //在sheet里创建第二行
        rownumset=2;
        HSSFRow row3=sheet.createRow(rownumset);
        //创建单元格并设置单元格内容
        row3.createCell(11).setCellValue("单位：小时");

//---------------------------------------------------------------------------------------
        //在sheet里创建第二行
        rownumset=3;
        int rownumfirst=rownumset;
        int rownumlast=rownumset+1;
        HSSFRow row4=sheet.createRow(rownumset);
        //创建单元格并设置单元格内容
        HSSFCell cell1_4=row4.createCell(0);
        HSSFCell cell2_4=row4.createCell(1);
        HSSFCell cell3_4=row4.createCell(2);
        HSSFCell cell4_4=row4.createCell(3);
        HSSFCell cell5_4=row4.createCell(4);
        HSSFCell cell6_4=row4.createCell(5);
        HSSFCell cell7_4=row4.createCell(6);
//        HSSFCell cell8=row4.createCell(7);
//        HSSFCell cell9=row4.createCell(8);
//        HSSFCell cell10=row4.createCell(9);
        HSSFCell cell11_4=row4.createCell(10);
        HSSFCell cell12_4=row4.createCell(11);
        HSSFCell cell13_4=row4.createCell(12);
        cell1_4.setCellValue("序号");
        cell2_4.setCellValue("员工编号");
        cell3_4.setCellValue("姓名");
        cell4_4.setCellValue("人员类别");
        cell5_4.setCellValue("科");
        cell6_4.setCellValue("加班原因");
        cell7_4.setCellValue("月度加班计划工时");
//        cell8.setCellValue("平时");
//        cell9.setCellValue("休息日");
//        cell10.setCellValue("节假日");
//        cell11.setCellValue("小计");
        cell11_4.setCellValue("休息日具体加班日期");
        cell12_4.setCellValue("节假日具体加班日期");
        cell13_4.setCellValue("科负责人确认");

//        row4.createCell(0).setCellValue("序号");

        CellRangeAddress CellRangeAddress0_4 = new CellRangeAddress(rownumfirst,rownumlast,0,0);
        sheet.addMergedRegion(CellRangeAddress0_4);
        CellRangeAddress CellRangeAddress1_4 = new CellRangeAddress(rownumfirst,rownumlast,1,1);
        sheet.addMergedRegion(CellRangeAddress1_4);
        CellRangeAddress CellRangeAddress2_4= new CellRangeAddress(rownumfirst,rownumlast,2,2);
        sheet.addMergedRegion(CellRangeAddress2_4);
        CellRangeAddress CellRangeAddress3_4 = new CellRangeAddress(rownumfirst,rownumlast,3,3);
        sheet.addMergedRegion(CellRangeAddress3_4);
        CellRangeAddress CellRangeAddress4_4 = new CellRangeAddress(rownumfirst,rownumlast,4,4);
        sheet.addMergedRegion(CellRangeAddress4_4);
        CellRangeAddress CellRangeAddress5_4 = new CellRangeAddress(rownumfirst,rownumlast,5,5);
        sheet.addMergedRegion(CellRangeAddress5_4);
        CellRangeAddress CellRangeAddress69_4 = new CellRangeAddress(rownumfirst,rownumfirst,6,9);
        sheet.addMergedRegion(CellRangeAddress69_4);


        CellRangeAddress CellRangeAddress10_4 = new CellRangeAddress(rownumfirst,rownumlast,10,10);
        sheet.addMergedRegion(CellRangeAddress10_4);
        CellRangeAddress CellRangeAddress11_4 = new CellRangeAddress(rownumfirst,rownumlast,11,11);
        sheet.addMergedRegion(CellRangeAddress11_4);
        CellRangeAddress CellRangeAddress12_4 = new CellRangeAddress(rownumfirst,rownumlast,12,12);
        sheet.addMergedRegion(CellRangeAddress12_4);

        setcellborder("宋体",8, sheet, wb,cell1_4);//设置单元格大小，字体大小
        setcellborder("宋体",8, sheet, wb,cell2_4);
        setcellborder("宋体",8, sheet, wb,cell3_4);
        setcellborder("宋体",8, sheet, wb,cell4_4);
        setcellborder("宋体",8, sheet, wb,cell5_4);
        setcellborder("宋体",8, sheet, wb,cell6_4);
        setcellborder("宋体",8, sheet, wb,cell7_4);
        setcellborder("宋体",8, sheet, wb,cell11_4);
        setcellborder("宋体",8, sheet, wb,cell12_4);
        setcellborder("宋体",8, sheet, wb,cell13_4);



//        sheet.addMergedRegion(new CellRangeAddress(rownumfirst,rownumlast,0,0));


        rownumset=4;
        HSSFRow row5=sheet.createRow(rownumset);
        //创建单元格并设置单元格内容
        HSSFCell cell7_5=row5.createCell(6);
        HSSFCell cell8_5=row5.createCell(7);
        HSSFCell cell9_5=row5.createCell(8);
        HSSFCell cell10_5=row5.createCell(9);
        cell7_5.setCellValue("平时");
        cell8_5.setCellValue("休息日");
        cell9_5.setCellValue("节假日");
        cell10_5.setCellValue("小计");
        setcellborder("宋体",8, sheet, wb,cell7_5);
        setcellborder("宋体",8, sheet, wb,cell8_5);
        setcellborder("宋体",8, sheet, wb,cell9_5);
        setcellborder("宋体",8, sheet, wb,cell10_5);

        setBorder(CellRangeAddress0_4, sheet, wb);
        setBorder(CellRangeAddress1_4, sheet, wb);//对于合并的单元格，此句必须放在其它属性设置完成之后执行
        setBorder(CellRangeAddress2_4, sheet, wb);
        setBorder(CellRangeAddress3_4, sheet, wb);
        setBorder(CellRangeAddress4_4, sheet, wb);
        setBorder(CellRangeAddress5_4, sheet, wb);
        setBorder(CellRangeAddress69_4, sheet, wb);
        setBorder(CellRangeAddress10_4, sheet, wb);
        setBorder(CellRangeAddress11_4, sheet, wb);
        setBorder(CellRangeAddress12_4, sheet, wb);

//        //.....省略部分代码



//-------------------------------数据库操作-------------------------
//        ScoreManager scoreManager = new ScoreManager();
//       List<ExamScore> list = scoreManager.getScoreList();
//       ExamScore[] strArray  = new ExamScore[list.size()];
//        list.toArray(strArray);
//        int rownumset_start=5;
//        for(int i=0; i<strArray.length; i++ ){
//            HSSFRow row=sheet.createRow(i+rownumset_start);
//            row.createCell(0).setCellValue(strArray[i].getName());
//            row.createCell(1).setCellValue(strArray[i].getClassGrade());
//            row.createCell(2).setCellValue(strArray[i].getScore());
//            row.createCell(3).setCellValue(strArray[i].getExamTime());
//        }
//
//        //输出Excel文件
//        OutputStream output=response.getOutputStream();
//        response.reset();
//        response.setHeader("Content-disposition", "attachment; filename=details.xls");
//        response.setContentType("application/msexcel");
//        wb.write(output);
//        output.close();


        AttentanceManager attentanceManager = new AttentanceManager();
        List<Attentance> list = attentanceManager.getAttentance_planList();
        Attentance [] strArray  = new Attentance [list.size()];
        list.toArray(strArray);
        int rownumset_start=5;
        for(int i=0; i<strArray.length; i++ ){
            HSSFRow row_new=sheet.createRow(i+rownumset_start);


            HSSFCell cell1_new=row_new.createCell(0);
            HSSFCell cell2_new=row_new.createCell(1);
            HSSFCell cell3_new=row_new.createCell(2);
            HSSFCell cell4_new=row_new.createCell(3);
            HSSFCell cell5_new=row_new.createCell(4);
            HSSFCell cell6_new=row_new.createCell(5);
            HSSFCell cell7_new=row_new.createCell(6);
            HSSFCell cell8_new=row_new.createCell(7);
            HSSFCell cell9_new=row_new.createCell(8);
            HSSFCell cell10_new=row_new.createCell(9);
            HSSFCell cell11_new=row_new.createCell(10);
            HSSFCell cell12_new=row_new.createCell(11);
            HSSFCell cell13_new=row_new.createCell(12);
            cell1_new.setCellValue(i+1);
            cell2_new.setCellValue(strArray[i].getMenberNo());
            cell3_new.setCellValue(strArray[i].getMenberName());
            cell4_new.setCellValue(strArray[i].getMenbertype());
            cell5_new.setCellValue(strArray[i].getDepartment());
            cell6_new.setCellValue(strArray[i].getAtt_project());
            cell7_new.setCellValue(strArray[i].getNormol_overtime());
            cell8_new.setCellValue(strArray[i].getOffday_overtime());
            cell9_new.setCellValue(strArray[i].getHoliday_overtime());
            cell10_new.setCellValue(strArray[i].getNormol_overtime()+strArray[i].getOffday_overtime()+strArray[i].getHoliday_overtime());//小计
            cell11_new.setCellValue(strArray[i].getOffday_apply_date());
            cell12_new.setCellValue(strArray[i].getHoliday_apply_date());
            setcellborder("宋体",8, sheet, wb,cell1_new);
            setcellborder("宋体",8, sheet, wb,cell2_new);
            setcellborder("宋体",8, sheet, wb,cell3_new);
            setcellborder("宋体",8, sheet, wb,cell4_new);
            setcellborder("宋体",8, sheet, wb,cell5_new);
            setcellborder("宋体",8, sheet, wb,cell6_new);
            setcellborder("宋体",8, sheet, wb,cell7_new);
            setcellborder("宋体",8, sheet, wb,cell8_new);
            setcellborder("宋体",8, sheet, wb,cell9_new);
            setcellborder("宋体",8, sheet, wb,cell10_new);
            setcellborder("宋体",8, sheet, wb,cell11_new);
            setcellborder("宋体",8, sheet, wb,cell12_new);
            setcellborder("宋体",8, sheet, wb,cell13_new);
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
