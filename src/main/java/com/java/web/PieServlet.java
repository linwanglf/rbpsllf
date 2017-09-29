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
public class PieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info( DateUtil.getCurrent()  + " Start PieServlet  ");
        request.setCharacterEncoding("utf-8");

        /**
         * jsonString: {"a":1,"b":2,"c":3,"d":4}
         */
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);


        String pieString =  JSON.toJSONString(map);
        System.out.println("pieString: " + pieString);
        try {
            ResponseUtil.write(response, pieString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info( DateUtil.getCurrent()  + " Finish PieServlet ");
    }
}
