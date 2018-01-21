package com.java.web;

import com.java.model.Simpledata;
import com.java.util.DateUtil;
import com.java.util.ResponseUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/28 16:07
 * Description:
 */
@Slf4j
public class ColumnServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //log.info( DateUtil.getCurrent()  + " Start ColumnServlet ");
        request.setCharacterEncoding("utf-8");
        Simpledata data_jane = new Simpledata("Jane" , new Integer[]{1 , 0 , 4});
        Simpledata data_john = new Simpledata("Jone" , new Integer[]{5 , 7 , 3});


//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add(data_jane);
//        jsonArray.add(data_john);
//        System.out.println("jsonArray: " + jsonArray);

        /**
        * series : [ { //指定数据列
              name : 'Jane', //数据列名   一定要这个列名
              data : [ 1, 0, 4 ] //数据   一定要这个数组名
          }, {
              name : 'John',
              data : [ 5, 7, 3 ]
          } ]
        */
        /**
         *
         * 使用aliba.JSON类与Net.JSONArray同样的效果.将对象转换为JSON格式的字符串，后续都使用com.alibaba.fastjson.JSON;省得混乱
         */
        List<Simpledata> list = new ArrayList<Simpledata>();
        list.add(data_jane);
        list.add(data_john);
        String jsonString =  JSON.toJSONString(list);
        System.out.println("jsonString: " + jsonString);
        try {
            ResponseUtil.write(response, jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //log.info( DateUtil.getCurrent()  + " Finish ColumnServlet ");
    }


}
