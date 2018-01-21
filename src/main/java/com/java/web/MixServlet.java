package com.java.web;

import com.alibaba.fastjson.JSON;

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
public class MixServlet extends HttpServlet {

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
        System.out.println( DateUtil.getCurrent()  + " Start MixServlet  ");
//        log.info( DateUtil.getCurrent()  + " Start MixServlet  ");
        request.setCharacterEncoding("utf-8");
        List list = new ArrayList();

        Map<Object,Object> zmap = new HashMap<>();
        zmap.put("type","column");
        zmap.put("name","小张");
        zmap.put("data",new Integer[]{3, 2, 1, 3, 4 });


        Map<Object,Object> pmap = new HashMap<>();
        pmap.put("type","column");
        pmap.put("name","小潘");
        pmap.put("data",new Integer[]{2, 3, 5, 7, 6 });


        Map<Object,Object>  wmap = new HashMap<>();
        wmap.put("type","column");
        wmap.put("name","小王");
        wmap.put("data",new Integer[]{4, 3, 3, 9, 3 });


        Map<Object,Object> lmap = new HashMap<>();
        lmap.put("type","spline");
        lmap.put("name","平均值");
        lmap.put("data",new Integer[]{2, 3, 1, 5, 2 });


        list.add(zmap);

        list.add(pmap);

        list.add(wmap);


        list.add(lmap);




        String mixString =  JSON.toJSONString(list);

        System.out.println("mixString: " + mixString);
        try {
            ResponseUtil.write(response, mixString);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        log.info( DateUtil.getCurrent()  + " Finish MixServlet ");
        System.out.println(DateUtil.getCurrent()  + " Finish MixServlet ");
    }
}
