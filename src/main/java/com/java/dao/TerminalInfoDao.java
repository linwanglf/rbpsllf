package com.java.dao;

import com.java.model.TerminalInfo;
import com.java.model.User;
import com.java.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/14 16:48
 * Description:
 */
@Slf4j
public class TerminalInfoDao  {

    public int save(Connection con, TerminalInfo terminalInfo)throws Exception{
        String sql="insert into t_collect_info(collect_time,ip,hostname,cpu_used_rate,cpu_temperature,memory_used_rate,gac_process) values( now(),?,?,?,?,?,?)";
        log.info( DateUtil.getCurrent() + " sql : " + sql );
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, terminalInfo.getIp());
        pstmt.setString(2, terminalInfo.getHostName());
        pstmt.setString(3, terminalInfo.getCpuUsedRate());
        pstmt.setString(4, terminalInfo.getCpuTemperature());
        pstmt.setString(5, terminalInfo.getMemoryUsedRate());
        pstmt.setString(6, terminalInfo.getGacProcess());
        return pstmt.executeUpdate();
    }
}
