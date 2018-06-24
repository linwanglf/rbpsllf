package com.java.web;

import com.java.dao.AuthDao;
import com.java.dao.RoleDao;
import com.java.dao.AttentanceDao;
import com.java.model.Auth;
import com.java.model.PageBean;
import com.java.model.User;
import com.java.model.Attentance;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
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

public class AttentanceServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	RoleDao roleDao=new RoleDao();
    AttentanceDao attentanceDao=new AttentanceDao();
	
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
        if("attentance_planList".equals(action)){
            this.attentance_planListAction(request, response);
        }else if("saveatt_plan".equals(action)){
            this.saveatt_planAction(request, response);
        }
        else if("deleteatt_plan".equals(action)){
            this.deleteatt_planAction(request, response);
        }
        else if("checkatt_plan".equals(action)){
            this.checkatt_planAction(request, response);
        }
        else if("cancelatt_plan".equals(action)){
            this.cancelatt_planAction(request, response);
        }
        else if("attentance_realList".equals(action)){
            this.attentance_realListAction(request, response);
        }else if("saveatt_real".equals(action)){
            this.saveatt_realAction(request, response);
        }
        else if("deleteatt_plan".equals(action)){
            this.deleteatt_realAction(request, response);
        }
        else if("checkatt_plan".equals(action)){
            this.checkatt_realAction(request, response);
        }
        else if("cancelatt_plan".equals(action)){
            this.cancelatt_realAction(request, response);
        }
	}


	



    private void attentance_planListAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page=request.getParameter("page");//在前台看不到这个变量，只要用了分页就自动传递,此参数是在前端searchmenber()时传递
        String rows=request.getParameter("rows");//在前台看不到这个变量，只要用了分页就自动传递,此参数是在前端searchmenber()时传递

        Attentance attentance=new Attentance();

        String s_division=request.getParameter("s_division");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--姓名
        String s_depart=request.getParameter("s_depart");//下拉框里面的内容,此参数是在前端searchmenber()时传递--部门ID
        String s_departmentname=request.getParameter("s_departmentname");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--工号
        String s_classname=request.getParameter("s_classname");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--工号
        //String s_departmentname="保全技术系";

        String s_menberName=request.getParameter("s_menberName");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--姓名
        String s_parentId=request.getParameter("s_parentId");//下拉框里面的内容,此参数是在前端searchmenber()时传递--部门ID
        String s_menberNo=request.getParameter("s_menberNo");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--工号

        if(StringUtil.isNotEmpty(s_division)){
            attentance.setDivision(s_division);

        }
        if(StringUtil.isNotEmpty(s_depart)){
            attentance.setDepartment(s_depart);

        }
        if(StringUtil.isNotEmpty(s_departmentname)){
            attentance.setDepartmentname(s_departmentname);
        }
        if(StringUtil.isNotEmpty(s_classname)){
            attentance.setClassname(s_classname);

        }

        if(StringUtil.isNotEmpty(s_menberName)){
            attentance.setMenberName(s_menberName);

        }
        if(StringUtil.isNotEmpty(s_parentId)){
            attentance.setParentId(Integer.parseInt(s_parentId));
        }
        if(StringUtil.isNotEmpty(s_menberNo)){
            attentance.setMenberNo(s_menberNo);
        }
        PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(attentanceDao.att_plan_applyList(con, pageBean,attentance));
            int total=attentanceDao.att_plan_applyCount(con,attentance);

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




    private void saveatt_planAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String attId=request.getParameter("attId");
        String menberNo=request.getParameter("menberNo");
        String menberName=request.getParameter("menberName");
        String att_type=request.getParameter("att_type");


        String att_project=request.getParameter("att_project") ;

        String apply_date_first=request.getParameter("applydate_first");
        String apply_date_second=request.getParameter("applydate_second");
        String apply_date_third=request.getParameter("applydate_third");
        String apply_date_fourth=request.getParameter("applydate_fourth");

        String applyer=request.getParameter("applyer");

        String checkstate=request.getParameter("checkstate");
        String checker=request.getParameter("checker");
        String check_date=request.getParameter("check_date");

        String houre_first=request.getParameter("houre_first");
        String houre_second=request.getParameter("houre_second");
        String houre_third=request.getParameter("houre_third");
        String houre_fourth=request.getParameter("houre_fourth");

        String menbertype=request.getParameter("menbertype");

        String classname=request.getParameter("tex_classname");
        String division=request.getParameter("tex_division");
        String department=request.getParameter("tex_depart");
        String departmentname=request.getParameter("tex_departmentname");
        //        String plan_type="7777";
        String plan_type=request.getParameter("plan_type");
        String parentId =request.getParameter("parentId");

        String apply_date=apply_date_first;
        String plan_houre=apply_date_first;

//        String attId=request.getParameter("attId");
//        String menberNo="7777";
//        String menberName="7777";
//        String att_type="7777";
//
//
//        String att_project="7777";
//
//        String apply_date_first="7777";
//        String apply_date_second="7777";
//        String apply_date_third="7777";
//        String apply_date_fourth="7777";
//
//        String applyer="7777";
//
//        String checkstate="7777";
//        String checker="7777";
//        String check_date="7777";
//
//        String houre_first="7777";
//        String houre_second="7777";
//        String houre_third="7777";
//        String houre_fourth="7777";
//
//        String menbertype="7777";
//
//        String classname="7777";
//        String division="7777";
//        String department="7777";
//        String departmentname="7777";
//        String plan_type="7777";
//        String parentId ="7777";
//
//        String apply_date="7777";
//        String plan_houre="7777";


        Attentance attentance=new Attentance( menberNo, menberName, att_type, att_project, apply_date,applyer, checkstate,
                checker, check_date, plan_houre,menbertype,division, department, departmentname, classname,plan_type)  ;

        if(StringUtil.isNotEmpty(attId)){
            attentance.setAttId(Integer.parseInt(attId));
        }
        attentance.setParentId(Integer.parseInt(parentId));

        Connection con=null;
        boolean isLeaf=false;
        try{
            JSONObject result=new JSONObject();
            con=dbUtil.getCon();
            int saveNums=0;
            if(StringUtil.isNotEmpty(attId)){//修改记录时调用，更新记录内容
                saveNums=attentanceDao.att_planUpdate(con, attentance);
            }else{//新增记录时调用，更新记录内容
                //isLeaf=attentanceDao.isLeafatt_plan(con, parentId);
//                if(isLeaf){//用于有节点关系的展开与闭合
//                    con.setAutoCommit(false);
//                    //attentanceDao.updateStateByAttId(con, "closed", parentId);//因为此模块没有节点，无需浪费内存
//                    saveNums=attentanceDao.att_planAdd(con, attentance);
//                    con.commit();
//                }else{

                saveNums=attentanceDao.att_planAdd(con, attentance);
//                }
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
    private void deleteatt_planAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String delIds=request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums=attentanceDao.att_planDelete(con, delIds);
            if(delNums>0){
                result.put("success", true);
                result.put("delNums", delNums);
            }else{
                result.put("errorMsg", "fail");
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

    private void checkatt_planAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String delIds=request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums=attentanceDao.att_planCheck(con, delIds);
            if(delNums>0){
                result.put("success", true);
                result.put("delNums", delNums);
            }else{
                result.put("errorMsg", "fail");
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

    private void cancelatt_planAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String delIds=request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums=attentanceDao.att_planCancel(con, delIds);
            if(delNums>0){
                result.put("success", true);
                result.put("delNums", delNums);
            }else{
                result.put("errorMsg", "fail");
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

    //-----------------------------------
    private void attentance_realListAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page=request.getParameter("page");//在前台看不到这个变量，只要用了分页就自动传递,此参数是在前端searchmenber()时传递
        String rows=request.getParameter("rows");//在前台看不到这个变量，只要用了分页就自动传递,此参数是在前端searchmenber()时传递

        Attentance attentance=new Attentance();
        String s_menberName=request.getParameter("s_menberName");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--姓名
        String s_parentId=request.getParameter("s_parentId");//下拉框里面的内容,此参数是在前端searchmenber()时传递--部门ID
        String s_menberNo=request.getParameter("s_menberNo");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--工号


        if(StringUtil.isNotEmpty(s_menberName)){
            attentance.setMenberName(s_menberName);

        }
        if(StringUtil.isNotEmpty(s_parentId)){
            attentance.setParentId(Integer.parseInt(s_parentId));
        }
        if(StringUtil.isNotEmpty(s_menberNo)){
            attentance.setMenberNo(s_menberNo);
        }
        PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(attentanceDao.att_real_applyList(con, pageBean,attentance));
            int total=attentanceDao.att_real_applyCount(con,attentance);

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




    private void saveatt_realAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String attId=request.getParameter("attId");
        String menberNo=request.getParameter("menberNo");
        String menberName=request.getParameter("menberName");
        String att_type=request.getParameter("att_type");


        String att_project=request.getParameter("att_project") ;

        String apply_date_first=request.getParameter("applydate_first");
        String apply_date_second=request.getParameter("applydate_second");
        String apply_date_third=request.getParameter("applydate_third");
        String apply_date_fourth=request.getParameter("applydate_fourth");

        String applyer=request.getParameter("applyer");

        String checkstate=request.getParameter("checkstate");
        String checker=request.getParameter("checker");
        String check_date=request.getParameter("check_date");

        String houre_first=request.getParameter("houre_first");
        String houre_second=request.getParameter("houre_second");
        String houre_third=request.getParameter("houre_third");
        String houre_fourth=request.getParameter("houre_fourth");

        String menbertype=request.getParameter("menbertype");

        String classname=request.getParameter("tex_classname");
        String division=request.getParameter("tex_division");
        String department=request.getParameter("tex_depart");
        String departmentname=request.getParameter("tex_departmentname");
        //        String plan_type="7777";
        String plan_type=request.getParameter("plan_type");
        String parentId =request.getParameter("parentId");

        String apply_date=apply_date_first;
        String plan_houre=apply_date_first;

//        String attId=request.getParameter("attId");
//        String menberNo="7777";
//        String menberName="7777";
//        String att_type="7777";
//
//
//        String att_project="7777";
//
//        String apply_date_first="7777";
//        String apply_date_second="7777";
//        String apply_date_third="7777";
//        String apply_date_fourth="7777";
//
//        String applyer="7777";
//
//        String checkstate="7777";
//        String checker="7777";
//        String check_date="7777";
//
//        String houre_first="7777";
//        String houre_second="7777";
//        String houre_third="7777";
//        String houre_fourth="7777";
//
//        String menbertype="7777";
//
//        String classname="7777";
//        String division="7777";
//        String department="7777";
//        String departmentname="7777";
//        String plan_type="7777";
//        String parentId ="7777";
//
//        String apply_date="7777";
//        String plan_houre="7777";


        Attentance attentance=new Attentance( menberNo, menberName, att_type, att_project, apply_date,applyer, checkstate,
                checker, check_date, plan_houre,menbertype,division, department, departmentname, classname,plan_type)  ;

        if(StringUtil.isNotEmpty(attId)){
            attentance.setAttId(Integer.parseInt(attId));
        }
        attentance.setParentId(Integer.parseInt(parentId));

        Connection con=null;
        boolean isLeaf=false;
        try{
            JSONObject result=new JSONObject();
            con=dbUtil.getCon();
            int saveNums=0;
            if(StringUtil.isNotEmpty(attId)){//修改记录时调用，更新记录内容
                saveNums=attentanceDao.att_realUpdate(con, attentance);
            }else{//新增记录时调用，更新记录内容
                //isLeaf=attentanceDao.isLeafatt_plan(con, parentId);
//                if(isLeaf){//用于有节点关系的展开与闭合
//                    con.setAutoCommit(false);
//                    //attentanceDao.updateStateByAttId(con, "closed", parentId);//因为此模块没有节点，无需浪费内存
//                    saveNums=attentanceDao.att_planAdd(con, attentance);
//                    con.commit();
//                }else{

                saveNums=attentanceDao.att_realAdd(con, attentance);
//                }
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
    private void deleteatt_realAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String delIds=request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums=attentanceDao.att_realDelete(con, delIds);
            if(delNums>0){
                result.put("success", true);
                result.put("delNums", delNums);
            }else{
                result.put("errorMsg", "fail");
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

    private void checkatt_realAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String delIds=request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums=attentanceDao.att_realCheck(con, delIds);
            if(delNums>0){
                result.put("success", true);
                result.put("delNums", delNums);
            }else{
                result.put("errorMsg", "fail");
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

    private void cancelatt_realAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String delIds=request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums=attentanceDao.att_realCancel(con, delIds);
            if(delNums>0){
                result.put("success", true);
                result.put("delNums", delNums);
            }else{
                result.put("errorMsg", "fail");
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
