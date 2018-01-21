package com.java.web;

import com.java.dao.TerminalInfoDao;
import com.java.model.TerminalInfo;
import com.java.util.DatabaseUtil;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/14 15:58
 * Description:
 */
@WebServlet(name = "MonitorServlet")
public class MonitorServlet extends HttpServlet {

    TerminalInfoDao terminalInfoDao = new TerminalInfoDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println( "Start Collect Information from Terminal");

        request.setCharacterEncoding("utf-8");
        String action=request.getParameter("action");
        if("TemperatureLine".equals(action)){
            displayLine(request, response);
        }{
            save(request, response );
        }
    }


    public void displayLine(HttpServletRequest request , HttpServletResponse response ){
        System.out.println( "Start monitor action=TemperatureLine");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","1");
        jsonObject.put("data","2");
        jsonArray.add(jsonObject);

        jsonObject.put("name","3");
        jsonObject.put("data","4");
        jsonArray.add(jsonObject);


        jsonObject.put("name","5");
        jsonObject.put("data","10");
        jsonArray.add(jsonObject);

        try{
            System.out.println(jsonArray.toString());
            ResponseUtil.write(response, jsonArray);

        }catch (Exception e){
             e.printStackTrace();
        }



    }

    public void save(HttpServletRequest request , HttpServletResponse response) {


        Connection conn = DatabaseUtil.getConnection();
        TerminalInfo terminalInfo = new TerminalInfo();
        try {

            terminalInfo.setIp(request.getParameter("ip"));
            terminalInfo.setHostName(request.getParameter("hostname"));
            terminalInfo.setCpuUsedRate(request.getParameter("cpu_used_rate"));
            terminalInfo.setCpuTemperature(request.getParameter("cpu_temperature"));
            terminalInfo.setMemoryUsedRate(request.getParameter("memory_used_rate"));
            terminalInfo.setGacProcess(request.getParameter("gacprocess"));
            System.out.println("Receive from Terminal " + terminalInfo);
            if (conn == null) {
                System.out.println(" Conn is Null ");
            }
            int insert = terminalInfoDao.save(conn, terminalInfo);

            ResponseUtil.write(response, "Receive from Terminal");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
