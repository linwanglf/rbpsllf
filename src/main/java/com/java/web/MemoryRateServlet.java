package com.java.web;

import com.alibaba.fastjson.JSONArray;
import com.java.manager.TemperatureManager;
import com.java.model.CpuRate;
import com.java.model.MemoryRate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/21 15:15
 * Description:
 */



public class MemoryRateServlet extends HttpServlet {
    private TemperatureManager temperatureManager = new TemperatureManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String strIp = request.getParameter("ipAddress");
        System.out.println("strIP "  + strIp );


        List<MemoryRate> list = temperatureManager.getLastMemoryUsedRateByIp( strIp);
        JSONArray jsonArray = new JSONArray();
        PrintWriter printWriter = response.getWriter();
        System.out.println(jsonArray.toJSONString(list));
        printWriter.print(jsonArray.toJSONString(list));
        printWriter.flush();
        printWriter.close();
    }
}

