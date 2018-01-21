package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.PageBean;
import com.java.model.Role;
import com.java.model.User;
import com.java.util.StringUtil;

public class RoleDao {

	public String getRoleNameById(Connection con,int id)throws Exception{
		String roleName=null;
		String sql="select roleName from t_role where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			roleName=rs.getString("roleName");
		}
		return roleName;
	}

	/**
	 * 根据角色ID返回MenuID
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getAuthIdsById(Connection con,int id)throws Exception{
		String authIds=null;
		String sql="select authIds from t_role where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			authIds=rs.getString("authIds");
		}
		return authIds;
	}

    public String getAuthIdsById_KQ(Connection con,int id)throws Exception{
        String authIds=null;
        String sql="select menberIds from t_role_menber where role_menberId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            authIds=rs.getString("menberIds");
        }
        return authIds;
    }

	/*
	 * return role
	 * 
	 */
	public ResultSet roleList(Connection con,PageBean pageBean,Role role)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_role");
		if(StringUtil.isNotEmpty(role.getRoleName())){
			sb.append(" and roleName like '%"+role.getRoleName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}




    public String getMenberIdsById(Connection con,int id)throws Exception{
        String menberIds=null;
        String sql="select menberIds from t_role_menber where role_menberId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            menberIds=rs.getString("menberIds");
        }
        return menberIds;
    }
	
	public int roleCount(Connection con,Role role)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_role ");
		if(StringUtil.isNotEmpty(role.getRoleName())){
			sb.append(" and roleName like '%"+role.getRoleName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int roleDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_role where roleId in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	public int roleAdd(Connection con,Role role)throws Exception{
		String sql="insert into t_role values(null,?,'',?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, role.getRoleName());
		pstmt.setString(2, role.getRoleDescription());
		return pstmt.executeUpdate();
	}
	
	public int roleUpdate(Connection con,Role role)throws Exception{
		String sql="update t_role set roleName=?,roleDescription=? where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, role.getRoleName());
		pstmt.setString(2, role.getRoleDescription());
		pstmt.setInt(3, role.getRoleId());
		return pstmt.executeUpdate();
	}
	
	public int roleAuthIdsUpdate(Connection con,Role role)throws Exception{
		String sql="update t_role set authIds=? where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, role.getAuthIds());
		pstmt.setInt(2, role.getRoleId());
		return pstmt.executeUpdate();
	}

    public int roleMenberIdsUpdate(Connection con,Role role)throws Exception{
        String sql="update t_role_menber set menberIds=? where role_menberId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, role.getAuthIds());
        pstmt.setInt(2, role.getRoleId());
        return pstmt.executeUpdate();
    }


        public ResultSet divisionList(Connection con,PageBean pageBean,Role role,String parentId)throws Exception{


            StringBuffer sb=new StringBuffer("select * from t_menber");
            sb.append(" and menu_level = '1' or menu_level = '2'  ");
            PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));


            return pstmt.executeQuery();
        }

        public ResultSet departList(Connection con,PageBean pageBean,Role role,String parentId_select)throws Exception{
        //StringBuffer sb=new StringBuffer("select * from t_menber parentId= ?");
        //sb.append(" and parentId= ? ");

        //PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
            String sql="select * from t_menber where parentId= ?";
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1, parentId_select);

        return pstmt.executeQuery();
        }

    public ResultSet departmentList(Connection con,PageBean pageBean,Role role,String parentId_select)throws Exception{
        String sql="select * from t_menber where parentId= ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId_select);
        return pstmt.executeQuery();
    }
    public ResultSet classList(Connection con,PageBean pageBean,Role role,String parentId_select)throws Exception{
        String sql="select * from t_menber where parentId= ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId_select);
        return pstmt.executeQuery();
    }
}
