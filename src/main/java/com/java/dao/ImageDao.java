package com.java.dao;

import com.java.model.Area;
import com.java.model.Image;
import com.java.model.PageBean;
import com.java.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Slf4j
public class ImageDao {

	public ResultSet imageList(Connection con, PageBean pageBean, Image image)throws Exception{
		StringBuffer sb=new StringBuffer("select * from image");
		if(StringUtil.isNotEmpty(image.getUserid())){
			sb.append(" and user_id = '" + image.getUserid()+ "'"  );
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		log.info("SQL: {}", sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	public int imageAdd(Connection con,Image image )throws Exception{
		String sql="insert into image(image_path,user_id,old_name) values(?,?,?)";
		System.out.println("strSQL: " + sql);

		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, image.getImagepath());
		pstmt.setString(2, image.getUserid());
		pstmt.setString(3, image.getOldname());
		return pstmt.executeUpdate();
	}
	
}
