package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.java.model.PageBean;
import com.java.model.Dict;
import com.java.util.DbUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DictDao {
	public ResultSet dictList(Connection con,PageBean pageBean,Dict dict)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_sym_dict u  ");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		//log.info(" dictList {}",sb );
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}


	public Map getDictByDictTypeId(String dictTypeId){
		Map<String,String > map =  new HashMap<String,String>();

		String sql="select DICT_NAME,DICT_DESC from T_SYM_DICT where DICT_TYPE=?";
		//log.info(" getDictByDictTypeId {}",sql );
		try {
			DbUtil dbUtil = new DbUtil();
			Connection con = dbUtil.getCon();
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dictTypeId);
			ResultSet rs=pstmt.executeQuery();
			while (rs.next()){
				map.put(rs.getString("DICT_DESC"),rs.getString("DICT_NAME"));
			}

		}catch (Exception e){
			e.printStackTrace();
		}

		return  map;
	}
	
	
	public int dictCount(Connection con,Dict dict)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_sym_dict u ");
		//log.info(" Sql {}",sb );
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int dictAdd(Connection con,Dict dict)throws Exception{
		String sql="insert into T_SYM_DICT(dict_type,dict_name,dict_desc) values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dict.getDictType());
		pstmt.setString(2, dict.getDictName() );
		pstmt.setString(3, dict.getDictDesc());
		return pstmt.executeUpdate();
	}
//
//	public int userUpdate(Connection con,User user)throws Exception{
//		String sql="update t_user set userName=?,password=?,roleId=?,userDescription=? where userId=?";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, user.getUserName());
//		pstmt.setString(2, user.getPassword());
//		pstmt.setInt(3, user.getRoleId());
//		pstmt.setString(4, user.getUserDescription());
//		pstmt.setInt(5, user.getUserId());
//		return pstmt.executeUpdate();
//	}
	

	
	public int dictDelete(Connection con,String dictIds)throws Exception{
		String sql="delete from t_sym_dict where dict_id in ("+dictIds+")";
		//log.info(" Sql {}",sql );
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

}
