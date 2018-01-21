package com.java.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.java.dao.AuthDao;
import com.java.dao.RoleDao;
import com.java.model.PageBean;
import com.java.model.Auth;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;

public class AuthServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	AuthDao authDao=new AuthDao();
	RoleDao roleDao=new RoleDao();
	
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
        if("menu".equals(action)){
            this.menuAction(request, response);
        }else if("authMenu".equals(action)){
            this.authMenuAction(request, response);
        }else if("roleMenber".equals(action)){
            this.roleMenberAction(request, response);
        }else if("menberList".equals(action)){
            this.menberListAction(request, response);
        }else if("personList".equals(action)){
            this.personListAction(request, response);
        }else if("authTreeGridMenu".equals(action)){
            this.authTreeGridMenuAction(request, response);
        }else if("menberTreeGridMenu".equals(action)){
            this.menberTreeGridMenuAction(request, response);
        }else if("save".equals(action)){
            this.saveAction(request, response);
        }else if("savemenber".equals(action)){
            this.savemenberAction(request, response);
        }else if("updatemenber".equals(action)){
            this.updatemenberAction(request, response);
        }
        else if("delete".equals(action)){
            this.deleteAction(request, response);
        }else if("deletemenber".equals(action)){
            this.deletemenberAction(request, response);
        }
	}

	private void menuAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parentId=request.getParameter("parentId");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			HttpSession session=request.getSession();
			User currentUser=(User)session.getAttribute("currentUser");
			String authIds=roleDao.getAuthIdsById(con, currentUser.getRoleId());
			JSONArray jsonArray=authDao.getAuthsByParentId(con, parentId,authIds);
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
	
	
	private void authMenuAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parentId=request.getParameter("parentId");
		String roleId=request.getParameter("roleId");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			String authIds=roleDao.getAuthIdsById(con, Integer.parseInt(roleId));
			JSONArray jsonArray=authDao.getCheckedAuthsByParentId(con, parentId,authIds);
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


	private void authTreeGridMenuAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parentId=request.getParameter("parentId");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONArray jsonArray=authDao.getListByParentId(con, parentId);
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
	
	private void saveAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String authId=request.getParameter("authId");
		String authName=request.getParameter("authName");
		String authPath=request.getParameter("authPath");
		String authDescription=request.getParameter("authDescription");
		String iconCls=request.getParameter("iconCls");
		String parentId =request.getParameter("parentId");
		Auth auth=new Auth(authName, authPath, authDescription, iconCls);
		if(StringUtil.isNotEmpty(authId)){
			auth.setAuthId(Integer.parseInt(authId));
		}else{
			auth.setParentId(Integer.parseInt(parentId));
		}
		Connection con=null;
		boolean isLeaf=false;
		try{
			JSONObject result=new JSONObject();
			con=dbUtil.getCon();
			int saveNums=0;
			if(StringUtil.isNotEmpty(authId)){
				saveNums=authDao.authUpdate(con, auth);
			}else{
				isLeaf=authDao.isLeaf(con, parentId);
				if(isLeaf){
					con.setAutoCommit(false);
					authDao.updateStateByAuthId(con, "closed", parentId);
					saveNums=authDao.authAdd(con, auth);
					con.commit();
				}else{
					saveNums=authDao.authAdd(con, auth);
				}
			}
			if(saveNums>0){
				result.put("success", true);
			}else{
				result.put("success", true);
				result.put("errorMsg", "???????");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			if(isLeaf){
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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
	
	private void deleteAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String authId=request.getParameter("authId");
		String parentId=request.getParameter("parentId");
		Connection con=null;
		int sonNum=-1;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			if(!authDao.isLeaf(con, authId)){
				result.put("errorMsg", "错误");
			}else{
				int delNums=0;
				sonNum=authDao.getAuthCountByParentId(con, parentId);
				if(sonNum==1){
					con.setAutoCommit(false);
					authDao.updateStateByAuthId(con, "open", parentId);
					delNums=authDao.authDelete(con, authId);
					con.commit();
				}else{
					delNums=authDao.authDelete(con, authId);
				}
				if(delNums>0){
					result.put("success", true);
				}else{
					result.put("errorMsg", "错误");
				}
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			if(sonNum==1){
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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


    private void roleMenberAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        String parentId=request.getParameter("parentId");
        String roleId=request.getParameter("roleId");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            String authIds=roleDao.getAuthIdsById_KQ(con, Integer.parseInt(roleId));
            JSONArray jsonArray=authDao.getCheckedAuthsByParentId_KQ(con, parentId,authIds);
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
    private void menberListAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parentId=request.getParameter("parentId");
        String roleId=request.getParameter("roleId");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            String menberIds=roleDao.getMenberIdsById(con, Integer.parseInt(roleId));//取得已经授权的项目menberIds
            JSONArray jsonArray=authDao.getCheckedMenberlistsByParentId(con, parentId,menberIds);
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

    private void personListAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page=request.getParameter("page");//在前台看不到这个变量，只要用了分页就自动传递,此参数是在前端searchmenber()时传递
        String rows=request.getParameter("rows");//在前台看不到这个变量，只要用了分页就自动传递,此参数是在前端searchmenber()时传递

        Auth auth=new Auth();
        String s_menberName=request.getParameter("s_menberName");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--姓名
        String s_parentId=request.getParameter("s_parentId");//下拉框里面的内容,此参数是在前端searchmenber()时传递--部门ID
        String s_menberNo=request.getParameter("s_menberNo");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--工号


        if(StringUtil.isNotEmpty(s_menberName)){
            auth.setMenberName(s_menberName);

        }
        if(StringUtil.isNotEmpty(s_parentId)){
            auth.setParentId(Integer.parseInt(s_parentId));
        }
        if(StringUtil.isNotEmpty(s_menberNo)){
            auth.setMenberNo(s_menberNo);
        }
        PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(authDao.personList(con, pageBean,auth));
            int total=authDao.personCount(con,auth);

            //JSONArray jsonArray=JsonUtil.formatRsToJsonArray(authDao.personList(con, pageBean,user));
            //int total=authDao.personCount(con,user);
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


    private void menberTreeGridMenuAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parentId=request.getParameter("parentId");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONArray jsonArray=authDao.getMenberListByParentId(con, parentId);
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

    private void savemenberAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menberId=request.getParameter("menberId");
        String menberName=request.getParameter("menberName");
        String menberPath=request.getParameter("menberPath");
        String parentId =request.getParameter("parentId");
        String menberDescription=request.getParameter("menberDescription");
        String iconCls=request.getParameter("iconCls");

        String homeaddress="9999";
        String politicalStatus="9999";
        String pursuits_talents="9999";
        String companyold="9999";//原来单位
        String certify="9999";
        String classtype="9999";
        String photo="9999";
        String prize="9999";
        String profession="9999";
        String shcool="9999";

        String phoneEMG="9999";
        String email="9999";
        String phone="9999";
        String iDcode="9999";
        String marriage="9999";
        String menbertype="9999";
        String sex="9999";
        String expense_center="9999";
        String position="9999";
        String entercompany_date="9999";

        String work_ege="9999";
        String birthday="9999";
        String classname="9999";
        String division="9999";
        String department="9999";
        String departmentname="9999";
        String menu_level="9999";
        String menberNo="000000";

        //Auth menber=new Auth(menberName, menberPath, menberDescription, iconCls,companyold);
        Auth menber=new Auth(menberName,menberPath, menberDescription, iconCls,homeaddress,politicalStatus,
                pursuits_talents,companyold,certify,classtype,photo,prize,profession,shcool,phoneEMG,email,
                phone,iDcode,marriage,menbertype,sex,expense_center,position,entercompany_date,work_ege,
                birthday,classname,division,department,departmentname, menu_level,menberNo);

        if(StringUtil.isNotEmpty(menberId)){
            menber.setMenberId(Integer.parseInt(menberId));
        }else{
            menber.setParentId(Integer.parseInt(parentId));
        }
        Connection con=null;
        boolean isLeaf=false;
        try{
            JSONObject result=new JSONObject();
            con=dbUtil.getCon();
            int saveNums=0;
            if(StringUtil.isNotEmpty(menberId)){
                saveNums=authDao.menberUpdate(con, menber);
            }else{
                isLeaf=authDao.isLeafmenber(con, parentId);
                if(isLeaf){
                    con.setAutoCommit(false);
                    authDao.updateStateByAuthIdmenber(con, "closed", parentId);
                    saveNums=authDao.menberAdd(con, menber);
                    con.commit();
                }else{
                    saveNums=authDao.menberAdd(con, menber);
                }
            }
            if(saveNums>0){
                result.put("success", true);
            }else{
                result.put("success", true);
                result.put("errorMsg", "fail");
            }
            ResponseUtil.write(response, result);
        }catch(Exception e){
            if(isLeaf){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
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

    private void updatemenberAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menberId=request.getParameter("menberId");
        String menberName=request.getParameter("menberName");
        String menberPath=request.getParameter("menberPath");
        String parentId =request.getParameter("parentId");


        String menberDescription=request.getParameter("menberDescription");
        String iconCls=request.getParameter("iconCls");



        String homeaddress=request.getParameter("homeaddress");
        String politicalStatus=request.getParameter("politicalStatus");
        String pursuits_talents=request.getParameter("pursuits_talents");
        String companyold=request.getParameter("companyold");
        String certify=request.getParameter("certify");
        String classtype=request.getParameter("classtype");
        String photo=request.getParameter("photo");
        String prize=request.getParameter("prize");
        String profession=request.getParameter("profession");
        String shcool=request.getParameter("shcool");

        String phoneEMG=request.getParameter("phoneEMG");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String iDcode=request.getParameter("iDcode");
        String marriage=request.getParameter("marriage");
        String menbertype=request.getParameter("menbertype");
        String sex=request.getParameter("sex");
        String expense_center=request.getParameter("expense_center");
        String position=request.getParameter("position");
        String entercompany_date=request.getParameter("entercompany_date");

        String work_ege=request.getParameter("work_ege");
        String birthday=request.getParameter("birthday");
//        String classname=request.getParameter("classname");
//        String division=request.getParameter("division");
//        String department=request.getParameter("depart");
//        String departmentname=request.getParameter("departmentname");
        String classname=request.getParameter("tex_classname");
        String division=request.getParameter("tex_division");
        String department=request.getParameter("tex_depart");
        String departmentname=request.getParameter("tex_departmentname");
       // String menu_level=request.getParameter("menu_level");
        String menu_level="0";
       String menberNo=request.getParameter("menberNo");

        Auth menber=new Auth(menberName,menberPath, menberDescription, iconCls,homeaddress,politicalStatus,
                pursuits_talents,companyold,certify,classtype,photo,prize,profession,shcool,phoneEMG,email,
                phone,iDcode,marriage,menbertype,sex,expense_center,position,entercompany_date,work_ege,
                birthday,classname,division,department,departmentname, menu_level,menberNo);

        if(StringUtil.isNotEmpty(menberId)){
            menber.setMenberId(Integer.parseInt(menberId));
        }else{
            menber.setParentId(Integer.parseInt(parentId));
        }
        Connection con=null;
        boolean isLeaf=false;
        try{
            JSONObject result=new JSONObject();
            con=dbUtil.getCon();
            int saveNums=0;
            if(StringUtil.isNotEmpty(menberId)){
                saveNums=authDao.menberUpdate(con, menber);
            }else{
                isLeaf=authDao.isLeafmenber(con, parentId);
                if(isLeaf){
                    con.setAutoCommit(false);
                    authDao.updateStateByAuthIdmenber(con, "closed", parentId);
                    saveNums=authDao.menberAdd(con, menber);
                    con.commit();
                }else{
                    saveNums=authDao.menberAdd(con, menber);
                }
            }
            if(saveNums>0){
                result.put("success", true);
            }else{
                result.put("success", true);
                result.put("errorMsg", "fail");
            }
            ResponseUtil.write(response, result);
        }catch(Exception e){
            if(isLeaf){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
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
    private void deletemenberAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menberId=request.getParameter("menberId");
        String parentId=request.getParameter("parentId");
        Connection con=null;
        int sonNum=-1;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            if(!authDao.isLeafmenber(con, menberId)){
                result.put("errorMsg", "错误");
            }else{
                int delNums=0;
                sonNum=authDao.getMenberCountByParentId(con, parentId);

                if(sonNum==1){//如果父亲节点只要当前这个需要删除的孩子，则需要更新其的state字段为closed
                    con.setAutoCommit(false);
                    authDao.updateStateByMenberId(con, "open", parentId);
                    delNums=authDao.menberDelete(con, menberId);
                    con.commit();
                }else{
                    result.put("success", true);
                    delNums=authDao.menberDelete(con, menberId);
                }

                if(delNums>0){
                    result.put("success", true);
                }else{
                    result.put("errorMsg", "错误");
                }
            }
            ResponseUtil.write(response, result);
        }catch(Exception e){
            if(sonNum==1){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
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
