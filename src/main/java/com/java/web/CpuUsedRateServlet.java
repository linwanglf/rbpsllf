package com.java.web;

import com.google.gson.Gson;
import com.java.manager.TemperatureManager;
import com.java.model.Temperature;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/21 15:15
 * Description:
 */



public class CpuUsedRateServlet extends HttpServlet {
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
        String strIp = request.getParameter("ip");
        System.out.println("strIP "  + strIp );

        Temperature temperatureGZ = temperatureManager.getLastCpuUsedRateByIp( strIp);
        List<Temperature > list = new ArrayList<>();
        list.add(temperatureGZ);

        Gson gson = new Gson();
        String data = gson.toJson(list);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(data);
        printWriter.flush();
        printWriter.close();
    }
}

