package com.java.dao;



import com.java.model.Auth;
import com.java.model.PageBean;
import com.java.model.Province;
import com.java.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Slf4j
public class ProvinceDao {

	/**
	 * 根据parentId,authId返回Menu对象
	 * @param con

	 * @return
	 * @throws Exception
	 */


	public ResultSet provinceList(Connection con, PageBean pageBean, Province province)throws Exception{
		StringBuffer sb=new StringBuffer("select * from province");
		if(StringUtil.isNotEmpty(province.getRegioncode())){
			sb.append(" and regioncode= '" + province.getRegioncode() + "'"  );
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));

		//log.info("SQL: {}", sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
//
//	public JSONArray getAuthByParentId(Connection con,String parentId,String authIds)throws Exception{
//		JSONArray jsonArray=new JSONArray();
//		String sql="select * from t_auth where parentId=? and authId in ("+authIds+")";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, parentId);
//		ResultSet rs=pstmt.executeQuery();
//		while(rs.next()){
//			JSONObject jsonObject=new JSONObject();
//			jsonObject.put("id", rs.getInt("authId"));
//			jsonObject.put("text", rs.getString("authName"));
//			if(!hasChildren(con, rs.getString("authId"), authIds)){
//				jsonObject.put("state", "open");
//			}else{
//				jsonObject.put("state", rs.getString("state"));
//			}
//			jsonObject.put("iconCls", rs.getString("iconCls"));
//			JSONObject attributeObject=new JSONObject();
//			attributeObject.put("authPath", rs.getString("authPath"));
//			jsonObject.put("attributes", attributeObject);
//			jsonArray.add(jsonObject);
//		}
//		return jsonArray;
//	}
//
//	private boolean hasChildren(Connection con,String parentId,String authIds)throws Exception{
//		String sql="select * from t_auth where parentId=? and authId in ("+authIds+")";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, parentId);
//		ResultSet rs=pstmt.executeQuery();
//		return rs.next();
//	}
//
//	/**
//	 * 返回所有的Menu
//	 * @param con
//	 * @param parentId
//	 * @param authIds
//	 * @return
//	 * @throws Exception
//	 */
//	public JSONArray getAuthsByParentId(Connection con,String parentId,String authIds)throws Exception{
//		JSONArray jsonArray=this.getAuthByParentId(con, parentId,authIds);
//		for(int i=0;i<jsonArray.size();i++){
//			JSONObject jsonObject=jsonArray.getJSONObject(i);
//			if("open".equals(jsonObject.getString("state"))){
//				continue;
//			}else{
//				jsonObject.put("children", getAuthsByParentId(con,jsonObject.getString("id"),authIds));
//			}
//		}
//		return jsonArray;
//	}
//
//	public JSONArray getCheckedAuthByParentId(Connection con,String parentId,String authIds)throws Exception{
//		JSONArray jsonArray=new JSONArray();
//		String sql="select * from t_auth where parentId=? ";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, parentId);
//		ResultSet rs=pstmt.executeQuery();
//		while(rs.next()){
//			JSONObject jsonObject=new JSONObject();
//			int authId=rs.getInt("authId");
//			jsonObject.put("id", authId);
//			jsonObject.put("text", rs.getString("authName"));
//			jsonObject.put("state", rs.getString("state"));
//			jsonObject.put("iconCls", rs.getString("iconCls"));
//			if(StringUtil.existStrArr(authId+"", authIds.split(","))){
//				jsonObject.put("checked", true);
//			}
//			JSONObject attributeObject=new JSONObject();
//			attributeObject.put("authPath", rs.getString("authPath"));
//			jsonObject.put("attributes", attributeObject);
//			jsonArray.add(jsonObject);
//		}
//		return jsonArray;
//	}
//
//	public JSONArray getCheckedAuthsByParentId(Connection con,String parentId,String authIds)throws Exception{
//		JSONArray jsonArray=this.getCheckedAuthByParentId(con, parentId,authIds);
//		for(int i=0;i<jsonArray.size();i++){
//			JSONObject jsonObject=jsonArray.getJSONObject(i);
//			if("open".equals(jsonObject.getString("state"))){
//				continue;
//			}else{
//				jsonObject.put("children", getCheckedAuthsByParentId(con,jsonObject.getString("id"),authIds));
//			}
//		}
//		return jsonArray;
//	}
//
//
//	public JSONArray getTreeGridAuthByParentId(Connection con,String parentId)throws Exception{
//		JSONArray jsonArray=new JSONArray();
//		String sql="select * from t_auth where parentId=? ";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, parentId);
//		ResultSet rs=pstmt.executeQuery();
//		while(rs.next()){
//			JSONObject jsonObject=new JSONObject();
//			jsonObject.put("id", rs.getInt("authId"));
//			jsonObject.put("text", rs.getString("authName"));
//			jsonObject.put("state", rs.getString("state"));
//			jsonObject.put("iconCls", rs.getString("iconCls"));
//			jsonObject.put("authPath", rs.getString("authPath"));
//			jsonObject.put("authDescription", rs.getString("authDescription"));
//			jsonArray.add(jsonObject);
//		}
//		return jsonArray;
//	}
//
//	public JSONArray getListByParentId(Connection con,String parentId)throws Exception{
//		JSONArray jsonArray=this.getTreeGridAuthByParentId(con, parentId);
//		for(int i=0;i<jsonArray.size();i++){
//			JSONObject jsonObject=jsonArray.getJSONObject(i);
//			if("open".equals(jsonObject.getString("state"))){
//				continue;
//			}else{
//				jsonObject.put("children", getListByParentId(con,jsonObject.getString("id")));
//			}
//		}
//		return jsonArray;
//	}
//
//	public int authAdd(Connection con,Auth auth)throws Exception{
//		String sql="insert into t_auth values(null,?,?,?,?,'open',?)";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, auth.getAuthName());
//		pstmt.setString(2, auth.getAuthPath());
//		pstmt.setInt(3, auth.getParentId());
//		pstmt.setString(4, auth.getAuthDescription());
//		pstmt.setString(5, auth.getIconCls());
//		return pstmt.executeUpdate();
//	}
//
//	public int authUpdate(Connection con,Auth auth)throws Exception{
//		String sql="update t_auth set authName=?,authPath=?,authDescription=?,iconCls=? where authId=?";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, auth.getAuthName());
//		pstmt.setString(2, auth.getAuthPath());
//		pstmt.setString(3, auth.getAuthDescription());
//		pstmt.setString(4, auth.getIconCls());
//		pstmt.setInt(5, auth.getAuthId());
//		return pstmt.executeUpdate();
//	}
//
//	public boolean isLeaf(Connection con,String authId)throws Exception{
//		String sql="select * from t_auth where parentId=?";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, authId);
//		ResultSet rs=pstmt.executeQuery();
//		return !rs.next();
//	}
//
//	public int updateStateByAuthId(Connection con,String state,String authId)throws Exception{
//		String sql="update t_auth set state=? where authId=?";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, state);
//		pstmt.setString(2, authId);
//		return pstmt.executeUpdate();
//	}
//
//	public int authDelete(Connection con,String authId)throws Exception{
//		String sql="delete from t_auth where authId=?";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, authId);
//		return pstmt.executeUpdate();
//	}
//
//	public int getAuthCountByParentId(Connection con,String parentId)throws Exception{
//		String sql="select count(*) as total from t_auth where parentId=?";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, parentId);
//		ResultSet rs=pstmt.executeQuery();
//		if(rs.next()){
//			return rs.getInt("total");
//		}else{
//			return 0;
//		}
//	}
}
