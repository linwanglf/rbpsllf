package com.java.web;

import com.java.dao.AuthDao;
import com.java.dao.PresentDao;
import com.java.dao.RoleDao;
import com.java.model.Auth;
import com.java.model.Present;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PresentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	PresentDao presentDao=new PresentDao();

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("list".equals(action)){
			this.listPresent(request, response);
		}else if("save".equals(action)){
			this.updatePresent(request, response);
		}
	}

	private void listPresent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        System.out.println("start List left number ");
        Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONArray jsonArray=presentDao.listPresents(con);
            System.out.println(jsonArray);
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
	

	private void updatePresent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String presentCode=request.getParameter("presentcode");
		Present present =  new Present();
		present.setPresentCode(presentCode);
		Connection con=null;
		try{
			JSONObject result=new JSONObject();
			con=dbUtil.getCon();
			int saveNums=0;
			saveNums=presentDao.updatePresetLeftNum(con,present);

			if(saveNums>0){
				result.put("success", true);
			}else{
				result.put("success", true);
				result.put("errorMsg", "update error");
			}
			ResponseUtil.write(response, result);
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
