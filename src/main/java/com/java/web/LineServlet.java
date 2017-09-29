package com.java.web;

import com.alibaba.fastjson.JSON;
import com.java.model.Simpledata;
import com.java.util.DateUtil;
import com.java.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/29 14:22
 * Description:
 */
@Slf4j
public class LineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * var jsondata = [{"data": [1, 0, 4, 8, 20, 30, 22, 33, 20, 18, 29], "name": "GuangZhou"}]; 返回的是JsonArray.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info( DateUtil.getCurrent()  + " Start LineServlet  ");
        request.setCharacterEncoding("utf-8");
        Simpledata a = new Simpledata("GuangZhou" , new Integer[]{1 , 0 , 4,8,20,30,22,33,20,18,29 });
        Simpledata b = new Simpledata("ShenZhen" , new Integer[]{10 , 5 , 5,8,10,20,12,23,30,28,29 });
        List<Simpledata> list = new ArrayList<Simpledata>();
        list.add(a);
        list.add(b);

        String lineString =  JSON.toJSONString(list);

        System.out.println("lineString: " + lineString);
        try {
            ResponseUtil.write(response, lineString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info( DateUtil.getCurrent()  + " Finish LineServlet ");
    }
}
