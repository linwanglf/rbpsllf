package com.java.web;


import com.java.dao.ProvinceDao;
import com.java.dao.RegionDao;
import com.java.model.Province;
import com.java.model.Region;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/27 17:30
 * Description:
 */
public class ProvinceServlet extends HttpServlet {

	DbUtil dbUtil=new DbUtil();
	ProvinceDao provinceDao=new ProvinceDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Enter RegionServlet ");
		request.setCharacterEncoding("utf-8");
		comBoList(request, response);

	}


	private void comBoList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con=null;
		String regioncode = request.getParameter("regioncode");
		Province province = new Province();
		province.setRegioncode(regioncode);
		try{
			con=dbUtil.getCon();
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("provincecode", "");
			jsonObject.put("provincename", "select...");
			jsonArray.add(jsonObject);

			jsonArray.addAll(JsonUtil.formatRsToJsonArray(provinceDao.provinceList(con,null,province)));
			System.out.println("jsonArray: " + jsonArray.toString());
			ResponseUtil.write(response, jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
