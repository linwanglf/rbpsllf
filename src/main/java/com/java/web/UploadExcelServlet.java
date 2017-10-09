package com.java.web;


import com.java.manager.ScoreManager;
import com.java.util.DateUtil;
import com.java.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/29 14:22
 * Description:
 */
@Slf4j
public class UploadExcelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info(DateUtil.getCurrent() + " Start UploadExcelServlet  ");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding(request.getCharacterEncoding());
        ScoreManager scoreManager = new ScoreManager();
        try {
            List<FileItem> list = upload.parseRequest(request); //将请求转换成FileItem List
            for (int i = 0; i < list.size(); i++) {
                FileItem item = list.get(i);
                if (item.getName().endsWith(".xls")||item.getName().endsWith(".xlsx")) {
                    // 说明是文件,不过这里最好限制一下
                    //helper.importXls(item.getInputStream());
                    List<List<String>> result = ExcelUtil.importXlsx(item.getInputStream());//item转换成输入流
                    scoreManager.inportList(result); //导入数据库
                    out.write("{\"result\":\"OK\"}"); //返回的JSON格式
                } else {
                    // 说明文件格式不符合要求
                    out.write("{\"result\":\"Invalid\"}");//返回的JSON格式
                }
            }
            out.flush();
            out.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

