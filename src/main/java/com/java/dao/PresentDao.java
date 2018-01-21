package com.java.dao;


import com.java.model.Auth;
import com.java.model.Present;
import com.java.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PresentDao {


	public JSONArray listPresents(Connection con)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from present ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("presentCode", rs.getString("present_code"));
			jsonObject.put("leftNumber", rs.getString("left_number"));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	
	public int updatePresetLeftNum(Connection con,Present present )throws Exception{
		String sql="update present set left_number= left_number-1,update_time=now() where present_code=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, present.getPresentCode());
		return pstmt.executeUpdate();
	}
	

}
