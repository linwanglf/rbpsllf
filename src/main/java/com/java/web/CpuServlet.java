package com.java.web;

import com.java.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/14 22:01
 * Description:
 */
public class CpuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println( "Start monitor action=TemperatureLine");
        JSONArray result = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","1");
        jsonObject.put("data","2");
        result.add(jsonObject);

        jsonObject.put("name","3");
        jsonObject.put("data","4");
        result.add(jsonObject);


        jsonObject.put("name","5");
        jsonObject.put("data","10");
        result.add(jsonObject);

        try{
            System.out.println(result.toString());
            ResponseUtil.write(response, result);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
