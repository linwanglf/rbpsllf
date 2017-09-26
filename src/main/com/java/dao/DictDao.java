package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.PageBean;
import com.java.model.Dict;
import com.java.util.StringUtil;

public class DictDao {


	public ResultSet userList(Connection con,PageBean pageBean,Dict user)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_user u ,t_role r where u.roleId=r.roleId and u.userType!=1 ");
		if(StringUtil.isNotEmpty(user.getUserName())){
			sb.append(" and u.userName like '%"+user.getUserName()+"%'");
		}
		if(user.getRoleId()!=-1){
			sb.append(" and u.roleId="+user.getRoleId());
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	
	public int userCount(Connection con,User user)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_user u ,t_role r where u.roleId=r.roleId and u.userType!=1 ");
		if(StringUtil.isNotEmpty(user.getUserName())){
			sb.append(" and u.userName like '%"+user.getUserName()+"%'");
		}
		if(user.getRoleId()!=-1){
			sb.append(" and u.roleId="+user.getRoleId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int userAdd(Connection con,User user)throws Exception{
		String sql="insert into t_user values(null,?,?,2,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3, user.getRoleId());
		pstmt.setString(4, user.getUserDescription());
		return pstmt.executeUpdate();
	}
	
	public int userUpdate(Connection con,User user)throws Exception{
		String sql="update t_user set userName=?,password=?,roleId=?,userDescription=? where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3, user.getRoleId());
		pstmt.setString(4, user.getUserDescription());
		pstmt.setInt(5, user.getUserId());
		return pstmt.executeUpdate();
	}
	

	
	public int userDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_user where userId in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

}
