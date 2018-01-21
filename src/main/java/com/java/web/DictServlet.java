package com.java.web;

import com.java.dao.DictDao;
import com.java.dao.RoleDao;
import com.java.dao.UserDao;
import com.java.model.Dict;
import com.java.model.PageBean;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@Slf4j
public class DictServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	DictDao dictDao = new DictDao();


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if ("list".equals(action)) {
			list(request, response);
		} else if ("save".equals(action)) {
			save(request, response);
		} else if ("delete".equals(action)) {
			delete(request, response);
		}

	}


	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Dict dict = new Dict();
		String s_dictType = request.getParameter("s_dictType");

		if (StringUtil.isNotEmpty(s_dictType)) {
			dict.setDictType(s_dictType);
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(dictDao.dictList(con, pageBean, dict));
			//log.info("jsonArray {} " ,jsonArray);
			int total = dictDao.dictCount(con, dict);
			result.put("rows", jsonArray);
			result.put("total", total);
			//log.info("result {} " ,result);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dictType=request.getParameter("dictType");
		String dictName=request.getParameter("dictName");
//		String dictId=request.getParameter("dictId");
		String dictDesc=request.getParameter("dictDesc");
		String isDefault=request.getParameter("isDefault");

		Dict dict=new Dict();
		dict.setDictType(dictType);
		dict.setDictName(dictName);
		dict.setDictDesc(dictDesc);
		dict.setIsDefault(isDefault);

//		if(StringUtil.isNotEmpty(userId)){
//			user.setUserId(Integer.parseInt(userId));
//		}
		Connection con=null;
		try{
			JSONObject result=new JSONObject();
			con=dbUtil.getCon();
			int saveNums=0;
//			if(StringUtil.isNotEmpty(userId)){
//				saveNums=userDao.userUpdate(con, user);
//			}else{
//				if(userDao.existUserWithUserName(con, userName)){
//					saveNums=-1;
//				}else{
//					saveNums=userDao.userAdd(con, user);
//				}

//			}
			saveNums = dictDao.dictAdd(con,dict);
			if(saveNums==-1){
				result.put("success", true);
				result.put("errorMsg", "添加字典失败");
			}else if(saveNums==0){
				result.put("success", true);
				result.put("errorMsg", "添加字典成功");
			}else{
				result.put("success", true);
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

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//log.info("Start delete Dict ");
		String dictIds=request.getParameter("dictId");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int delNums=dictDao.dictDelete(con, dictIds);
			if(delNums>0){
				result.put("success", true);
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "delete failed");
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
