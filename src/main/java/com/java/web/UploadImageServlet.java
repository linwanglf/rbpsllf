package com.java.web;

import com.java.manager.ImageManager;
import com.java.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/10/10 9:31
 * Description:
 */
@Slf4j
public class UploadImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // log.info(DateUtil.getCurrent() + " Start UploadImageServlet ");
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        if(ServletFileUpload.isMultipartContent(request)){
            try{
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(10 * 1024 * 1024 );//
                List<FileItem> fileItemList = upload.parseRequest(request);
                Iterator<FileItem> fileItemIterator = fileItemList.iterator();
                while (fileItemIterator.hasNext()){
                    FileItem fileItem = fileItemIterator.next();
                    if( fileItem.isFormField()){//普通表单
                        String name = fileItem.getFieldName();
                        String value = fileItem.getString("utf-8");
                        System.out.println(name + " = " + value  );
                    }else{
                        String fileName = fileItem.getName();//文件名称
                        System.out.println("Filename = " + fileName );
                        String fileSuffix = fileName.substring(fileName.lastIndexOf('.'));
                        System.out.println("File Suffix = " + fileSuffix  );

                        String newFileName = DateUtil.getCurrent(DateUtil.YMDHMS)+fileSuffix;
                        System.out.println("New File Name = " +  newFileName );

                        String filePath = "/images/";

                        File newFile = new File(  this.getServletContext().getRealPath( "/")+filePath+newFileName );
                        //关键在通过getRealPath获取到真实的路径. 写到服务器上的文件
                        fileItem.write(newFile); //输出到新文件
                        fileItem.delete();
                        ImageManager imageManager = new ImageManager();
                        imageManager.saveImage(filePath,newFileName); //路径入库

                        session.setAttribute("image_name", "/images/"+newFileName);
                        response.sendRedirect(request.getContextPath() + "/excel/imageupload.jsp"); //重定向回imageupload.jsp
                    }
                }

            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
}
