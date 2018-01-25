package com.java.dao;

import com.java.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/21 15:23
 * Description:
 */
@Slf4j
public class TemperatureDao {
    /**
     * 返回最新的10条CPU温度
     * @param conn
     * @param strIP
     * @return
     * @throws Exception
     */
    public ResultSet lastCpuTemperature(Connection conn, String strIP )throws Exception{
        String strSql = " select collect_time,cpu_temperature  from t_collect_info where ip= ?  order by collect_time DESC limit  20 ";
        log.info( DateUtil.getCurrent() + " strSql : " + strSql );
        PreparedStatement pstmt=conn.prepareStatement(strSql);
        pstmt.setString(1,strIP );
        return pstmt.executeQuery();
    }

    /**
     * 返回最新的10条CPU使用率
     * @param conn
     * @param strIP
     * @return
     * @throws Exception
     */
    public ResultSet lastCpuUsedRate(Connection conn,String strIP )throws Exception{
        String strSql = " select collect_time,cpu_used_rate  from t_collect_info where ip= ?  order by collect_time DESC  limit  20 ";
        log.info( DateUtil.getCurrent() + " strSql : " + strSql );
        PreparedStatement pstmt=conn.prepareStatement(strSql);
        pstmt.setString(1,strIP );
        return pstmt.executeQuery();
    }

    /**
     * 返回最新的10条内存使用率
     * @param conn
     * @param strIP
     * @return
     * @throws Exception
     */
    public ResultSet lastMemoryUsedRate(Connection conn,String strIP )throws Exception{
        String strSql = " select collect_time,memory_used_rate  from t_collect_info where ip= ?  order by collect_time DESC limit  20 ";
        log.info( DateUtil.getCurrent() + " strSql : " + strSql );
        PreparedStatement pstmt=conn.prepareStatement(strSql);
        pstmt.setString(1,strIP );
        return pstmt.executeQuery();
    }

    /**
     * 返回最新的10条进程状态
     * @param conn
     * @param strIP
     * @return
     * @throws Exception
     */
    public ResultSet lastGacProcessUsedRate(Connection conn,String strIP )throws Exception{
        String strSql = " select collect_time,gac_process  from t_collect_info where ip= ?  order by collect_time DESC limit  20 ";
        log.info( DateUtil.getCurrent() + " strSql : " + strSql );
        PreparedStatement pstmt=conn.prepareStatement(strSql);
        pstmt.setString(1,strIP );
        return pstmt.executeQuery();
    }

    /**
     * 返回最新10条监控信息
     * @param conn
     * @param strIP
     * @return
     * @throws Exception
     */
    public ResultSet lastTerminalInfo( Connection conn, String strIP ) throws Exception{

        String strSql = " select collect_time,ip,hostname,cpu_used_rate,cpu_temperature,memory_used_rate,gac_process " +
                        "  from t_collect_info where ip= ?  order by collect_time DESC limit  10 ";
        log.info( DateUtil.getCurrent() + " strSql : " + strSql );
        PreparedStatement pstmt=conn.prepareStatement(strSql);
        pstmt.setString(1,strIP );
        return pstmt.executeQuery();
    }

    /**
     * 根据时间跨度返回监控信息
     * @param connection
     * @param strIp
     * @param beginTime
     * @param endTime
     * @return
     * @throws Exception
     */

    public ResultSet selectTerminalByIpAndTime(Connection connection, String strIp, String beginTime, String endTime ) throws Exception{

        String strSql = " select collect_time,ip,hostname,cpu_used_rate,cpu_temperature,memory_used_rate,gac_process " +
                "  from t_collect_info where ip= ?  and collect_time BETWEEN  ?  and  ?  order by collect_time DESC  limit 10 ";
        log.info( DateUtil.getCurrent() + " strSql : " + strSql );
        PreparedStatement pstmt=connection.prepareStatement(strSql);
        pstmt.setString(1,strIp );
        pstmt.setDate(2,Date.valueOf(beginTime));
        pstmt.setDate(3, Date.valueOf(endTime));
        return pstmt.executeQuery();
    }



}
