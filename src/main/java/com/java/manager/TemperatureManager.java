package com.java.manager;

import com.java.dao.TemperatureDao;
import com.java.model.Temperature;
import com.java.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Random;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/21 15:21
 * Description:
 */
public class TemperatureManager {

    private  Connection conn = DatabaseUtil.getConnection();

    private TemperatureDao temperatureDao=new TemperatureDao();

    public Temperature getLastTemperatureByIp(String strIp){
        Temperature temperature = new Temperature();
        int[] temp= new int[10];
        Random rand = new Random();
        int randNum = 0;
        try{
            int i = 0;
            ResultSet resultSet = temperatureDao.lastCpuTemperature(conn,strIp);
            while (resultSet.next()){
                randNum= rand.nextInt(20);
                //temp[i] = Integer.valueOf(resultSet.getString("cpu_temperature").substring(1,3)).intValue() + randNum ;
                temp[i] = Integer.valueOf(resultSet.getString("cpu_temperature")).intValue();
                i ++;

            }

        }catch (Exception e ){
            e.printStackTrace();
        }
        temperature.setData(temp);
        temperature.setName(strIp);
        return  temperature;
    }


    public Temperature getLastCpuUsedRateByIp(String strIp){
        Temperature temperature = new Temperature();
        int[] temp= new int[10];
        try{
            int i = 0;
            ResultSet resultSet = temperatureDao.lastCpuUsedRate(conn,strIp);
            while (resultSet.next()){

                //temp[i] = Integer.valueOf(resultSet.getString("cpu_temperature").substring(1,3)).intValue()  ;
                temp[i] = Integer.valueOf(resultSet.getString("cpu_used_rate")).intValue();
                i ++;

            }

        }catch (Exception e ){
            e.printStackTrace();
        }
        temperature.setData(temp);
        temperature.setName(strIp);
        return  temperature;
    }

}
