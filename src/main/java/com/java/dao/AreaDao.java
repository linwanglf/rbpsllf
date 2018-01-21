package com.java.dao;

import com.java.model.Area;
import com.java.model.PageBean;
import com.java.model.Region;
import com.java.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Slf4j
public class AreaDao {

	public ResultSet areaList(Connection con, PageBean pageBean, Area area)throws Exception{
		StringBuffer sb=new StringBuffer("select * from area");
		if(StringUtil.isNotEmpty(area.getCitycode())){
			sb.append(" and citycode = '" + area.getCitycode()+ "'"  );
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		//log.info("SQL: {}", sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
}
