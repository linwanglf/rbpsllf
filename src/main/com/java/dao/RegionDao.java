package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.Login;
import com.java.model.Region;
import com.java.model.PageBean;
import com.java.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegionDao {

	public ResultSet regionList(Connection con, PageBean pageBean, Region region)throws Exception{
		StringBuffer sb=new StringBuffer("select * from region");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		log.info("SQL: {}", sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
}
