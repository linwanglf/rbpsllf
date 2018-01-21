package com.java.dao;

import com.java.model.City;
import com.java.model.Login;
import com.java.model.PageBean;
import com.java.util.DateUtil;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
       /// log.info("SQL: {}", sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    public int scoreAdd(Connection con,List rowList )throws Exception{
        String sql="insert into t_examscore(name,classgrade,score,examtime) values(?,?,?,?)";
        System.out.println("strSQL: " + sql);

        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, rowList.get(0).toString());
        pstmt.setString(2, rowList.get(1).toString());
        pstmt.setString(3, rowList.get(2).toString());
        pstmt.setString(4,rowList.get(3).toString());

        return pstmt.executeUpdate();
    }

}
