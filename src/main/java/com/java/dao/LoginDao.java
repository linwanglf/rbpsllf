package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.Login;
import com.java.model.PageBean;
import com.java.model.User;
import com.java.util.StringUtil;

public class LoginDao {

	public int loginAdd(Connection con,Login login)throws Exception{
		String sql="insert into t_login_record(appid,shopid,coinlimit,coincost,secretkey,timestamp) values(?,?,?,?,?,now())";
		System.out.println("strSQL: " + sql);
		
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, login.getAppid());
		pstmt.setString(2, login.getShopid());
		pstmt.setString(3, login.getCoinlimit());		
		pstmt.setString(4, login.getCoincost());
		pstmt.setString(5, login.getSecretkey());		
		return pstmt.executeUpdate();
	}
	
	
	public ResultSet loginList(Connection con,PageBean pageBean,Login login)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_login_record where 1=1 ");
		
		if(StringUtil.isNotEmpty(login.getAppid())){
			sb.append(" and appid like '%"+login.getAppid()+"%'");
		}
		if(StringUtil.isNotEmpty(login.getShopid())){
			sb.append(" and shopid like '%"+login.getShopid()+"%'");
		}
		
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		System.out.println("strSQL: " + sb.toString());
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	/*
	 * return the total number of the login record
	 */
	public int loginCount(Connection con,Login login)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_login_record where 1=1 ");
		if(StringUtil.isNotEmpty(login.getAppid())){
			sb.append(" and appid like '%"+login.getAppid()+"%'");
		}
		if(StringUtil.isNotEmpty(login.getShopid())){
			sb.append(" and shopid like '%"+login.getShopid()+"%'");
		}
		System.out.println("strSQL: " + sb.toString());
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/*
	 * Delete Login Record
	 * 
	 */
	public int loginDelete(Connection con,String delIds)throws Exception{
		
	//	StringBuffer sb=new StringBuffer("delete from t_login_record where loginid in ("+delIds+")");
		String sql="delete from t_login_record where loginid in ("+delIds+")";
		System.out.println("strSQL: " + sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	
}
