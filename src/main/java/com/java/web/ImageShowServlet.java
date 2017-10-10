package com.java.web;

import com.java.manager.ImageManager;
import com.java.model.Image;
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
 * DATETIME:2017/10/10 11:30
 * Description:
 */
@Slf4j
public class ImageShowServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info(DateUtil.getCurrent() + " Start ImageShowServlet ");
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        ImageManager imageManager = new ImageManager();
        Image image = new Image();
        image.setUserid("01");
       List<Image> list = imageManager.getImageList(image);
       Iterator<Image> iterator = list.iterator();
       String filePath = "";
       while (iterator.hasNext()){
           Image image1 = iterator.next();
           filePath = image1.getImagepath();
       }
       session.setAttribute("show_image_path",filePath);
        response.sendRedirect(request.getContextPath() + "/excel/imageupload.jsp");
    }
}
