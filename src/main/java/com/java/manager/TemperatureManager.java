package com.java.manager;

import com.java.dao.TemperatureDao;
import com.java.model.CpuRate;
import com.java.model.GacProcess;
import com.java.model.MemoryRate;
import com.java.model.Temperature;
import com.java.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/21 15:21
 * Description:
 */
public class TemperatureManager {

    private  Connection conn = DatabaseUtil.getConnection();

    private TemperatureDao temperatureDao=new TemperatureDao();

    public List<Temperature> getLastTemperatureByIp(String strIp){
        List<Temperature> list = new ArrayList<>();

        try{
            ResultSet resultSet = temperatureDao.lastCpuTemperature(conn,strIp);
            while (resultSet.next()){
                Temperature temperature = new Temperature();
                temperature.setXKey( resultSet.getString("collect_time") );
                temperature.setYValue( resultSet.getString("cpu_temperature")  );
                list.add(temperature);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return  list;
    }


    public List<CpuRate> getLastCpuUsedRateByIp(String strIp){
        List<CpuRate> list = new ArrayList<>();
        try{
            ResultSet resultSet = temperatureDao.lastCpuUsedRate(conn,strIp);
            while (resultSet.next()){
                CpuRate cpuRate = new CpuRate();
                cpuRate.setXKey( resultSet.getString("collect_time") );
                cpuRate.setYValue( resultSet.getString("cpu_used_rate")  );
                list.add(cpuRate);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return  list;
    }


    public List<MemoryRate> getLastMemoryUsedRateByIp(String strIp){
        List<MemoryRate> list = new ArrayList<>();
        try{
            ResultSet resultSet = temperatureDao.lastMemoryUsedRate(conn,strIp);
            while (resultSet.next()){
                MemoryRate memoryRate = new MemoryRate();
                memoryRate.setXKey( resultSet.getString("collect_time") );
                memoryRate.setYValue( resultSet.getString("memory_used_rate")  );
                list.add(memoryRate);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return  list;
    }


    public List<GacProcess> getLastGacProcessByIp(String strIp){
        List<GacProcess> list = new ArrayList<>();
        try{
            ResultSet resultSet = temperatureDao.lastGacProcessUsedRate(conn,strIp);
            while (resultSet.next()){
                GacProcess gacProcess = new GacProcess();
                gacProcess.setXKey( resultSet.getString("collect_time") );
                gacProcess.setYValue( resultSet.getString("gac_process")  );
                list.add(gacProcess);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return  list;
    }

}
