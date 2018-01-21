package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.Login;
import com.java.model.Region;
import com.java.model.PageBean;
import com.java.util.StringUtil;

public class RegionDao {

	public ResultSet regionList(Connection con,PageBean pageBean,Region region)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_region where 1=1 ");
		
//		if(StringUtil.isNotEmpty(login.getAppid())){
//			sb.append(" and appid like '%"+login.getAppid()+"%'");
//		}
//		if(StringUtil.isNotEmpty(login.getShopid())){
//			sb.append(" and shopid like '%"+login.getShopid()+"%'");
//		}
		
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		System.out.println("strSQL: " + sb.toString());
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	/*
	 * 
	 * 
	 */
	
	/*
	 * return the total number of the login record
	 */
	public int regionCount(Connection con,Region region)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_region where 1=1 ");
//		if(StringUtil.isNotEmpty(login.getAppid())){
//			sb.append(" and appid like '%"+login.getAppid()+"%'");
//		}
//		if(StringUtil.isNotEmpty(login.getShopid())){
//			sb.append(" and shopid like '%"+login.getShopid()+"%'");
//		}
		System.out.println("strSQL: " + sb.toString());
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
}
