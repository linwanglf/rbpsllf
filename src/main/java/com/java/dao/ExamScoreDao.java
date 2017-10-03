package com.java.dao;

import com.java.model.City;
import com.java.model.PageBean;
import com.java.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/10/3 22:03
 * Description:
 */
@Slf4j
public class ExamScoreDao {

    /**
     * 根据parentId,authId返回Menu对象
     * @param con
     * @return
     * @throws Exception
     */

    public ResultSet scoreList(Connection con, PageBean pageBean)throws Exception{
        StringBuffer sb=new StringBuffer("select * from t_examscore");
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        log.info("SQL: {}", sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

}
