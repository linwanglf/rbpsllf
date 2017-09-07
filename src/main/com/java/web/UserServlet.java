package com.java.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.java.dao.RoleDao;
import com.java.dao.UserDao;
import com.java.model.PageBean;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;

public class UserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	RoleDao roleDao=new RoleDao();

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
		String action=request.getParameter("action");
		if("login".equals(action)){
			login(request, response);			
		}else if("logout".equals(action)){
			logout(request, response);
		}else if("modifyPassword".equals(action)){
			modifyPassword(request, response);
		}else if("list".equals(action)){
			list(request, response);
		}else if("save".equals(action)){
			save(request, response);
		}else if("delete".equals(action)){
			delete(request, response);
		}
		
	}

	private void modifyPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String newPassword=request.getParameter("newPassword");
		User user=new User();
		user.setUserId(Integer.parseInt(userId));
		user.setPassword(newPassword);
		Connection con=null;
		try{
			JSONObject result=new JSONObject();
			con=dbUtil.getCon();
			int updateNum=userDao.modifyPassword(con, user);
			if(updateNum>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "�޸�����ʧ�ܣ�");
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

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String imageCode=request.getParameter("imageCode");
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("imageCode", imageCode);
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
			request.setAttribute("error", "username can not be null");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(StringUtil.isEmpty(imageCode)){
			request.setAttribute("error", "register code can not be null");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(!imageCode.equals(session.getAttribute("sRand"))){
			request.setAttribute("error", "rand number is wrong");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		User user=new User(userName, password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				request.setAttribute("error", "username is not exist");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				String roleName=roleDao.getRoleNameById(con, currentUser.getRoleId());
				currentUser.setRoleName(roleName);
				session.setAttribute("currentUser", currentUser);
				response.sendRedirect("main.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		User user=new User();
		String s_userName=request.getParameter("s_userName");
		String s_roleId=request.getParameter("s_roleId");
		if(StringUtil.isNotEmpty(s_userName)){
			user.setUserName(s_userName);
		}
		if(StringUtil.isNotEmpty(s_roleId)){
			user.setRoleId(Integer.parseInt(s_roleId));
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(userDao.userList(con, pageBean,user));
			int total=userDao.userCount(con,user);
			result.put("rows", jsonArray);
			result.put("total", total);
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
	
	private void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String roleId=request.getParameter("roleId");
		String userDescription=request.getParameter("userDescription");
		String userId=request.getParameter("userId");
		User user=new User(userName, password, Integer.parseInt(roleId), userDescription);
		if(StringUtil.isNotEmpty(userId)){
			user.setUserId(Integer.parseInt(userId));
		}
		Connection con=null;
		try{
			JSONObject result=new JSONObject();
			con=dbUtil.getCon();
			int saveNums=0;
			if(StringUtil.isNotEmpty(userId)){
				saveNums=userDao.userUpdate(con, user);
			}else{
				if(userDao.existUserWithUserName(con, userName)){
					saveNums=-1;
				}else{
					saveNums=userDao.userAdd(con, user);					
				}
			}
			if(saveNums==-1){
				result.put("success", true);
				result.put("errorMsg", "添加用户失败");
			}else if(saveNums==0){
				result.put("success", true);
				result.put("errorMsg", "用户添加成功");
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
		String delIds=request.getParameter("delIds");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int delNums=userDao.userDelete(con, delIds);
			if(delNums>0){
				result.put("success", true);
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "ɾ��ʧ��");
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
