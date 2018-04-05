package com.java.dao;

import com.java.model.TerminalInfo;
import com.java.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/4/5 14:29
 * Description:
 */
@Slf4j
public class RfidInfoDao {

    public int save(Connection con, String epcstring)throws Exception{
        String sql="insert into t_rfid_record(record_time,epcstring ) values( now(),?)";
        log.info( DateUtil.getCurrent() + " sql : " + sql );
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, epcstring);
        return pstmt.executeUpdate();
    }
}
