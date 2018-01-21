package com.java.web;

import com.java.dao.RoleDao;
import com.java.dao.SelectDao;
import com.java.model.Select;
import com.java.model.PageBean;
import com.java.model.User;
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

public class SelectServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	SelectDao selectDao=new SelectDao();
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
       if("authMenu".equals(action)){
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
        else if("selectpresentNO".equals(action)){
            this.deletemenberAction(request, response);
        }
        else if("selectStoreCount".equals(action)){
            this.selectStoreCountAction(request, response);

        } else if("update".equals(action)){
            this.updatePresent(request, response);
       }

	}

	private void selectStoreCountAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        System.out.println("start List left number ");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            HttpSession session=request.getSession();
            User currentUser=(User)session.getAttribute("currentUser");

            String UserNO=currentUser.getUserName();//登录人员的工号
            Select select =  new Select();

            selectDao.QuerySelct(con,select,UserNO);//根据工号查询登录人员的所有基础信息

            //String ClassNO=select.getClassname();//所属班
            String Department=select.getDepartment();//所属班
            JSONArray jsonArray=selectDao.storecountList(con,Department);

            System.out.println(jsonArray);//将所有信息打印
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
        String product_store_NO=request.getParameter("product_store_NO");//根据选择物品编号
        Select select =  new Select();
        select.setProduct_store_NO(product_store_NO);

        Connection con=null;
        try{
            JSONObject result=new JSONObject();
            con=dbUtil.getCon();
            //-----------------------------以下程序段执行前应该判断当前操作者是否已经选择过了，防止重复操作--------------------------------------
            selectDao.QuerySelctProductName(con,select,product_store_NO);//根据物品编号查询名称

            HttpSession session=request.getSession();
            User currentUser=(User)session.getAttribute("currentUser");
            String UserNO=currentUser.getUserName();//登录人员的工号

            //是否唯一投票选择-----------------------------
            int checkonly=selectDao.checkonlyselectBymenberNo(con,UserNO);
            System.out.println(Integer.toString(checkonly));//

            if (checkonly>0) {//已投票
                result.put("error", true);

            }
            else {//未投

                //增加判断数量是否已经为零
                selectDao.QuerySelct(con, select, UserNO);//根据工号查询登录人员的所有基础信息
                System.out.println("start username and userNO ");
                String ClassNO = select.getDepartment();

                int checkzero=selectDao.checkzero(con, select, ClassNO);//
                System.out.println("store number:" + checkzero);
                if (checkzero==0) {//数量为0
                    result.put("zeroo", true);
                }
                else {
//                    selectDao.QuerySelct(con, select, UserNO);//根据工号查询登录人员的所有基础信息
//                System.out.println("start username and userNO ");
//                String ClassNO = select.getDepartment();
                int saveNums = 0;
                saveNums = selectDao.updatePresetLeftNum(con, select, ClassNO);//更新在库情况

                if (saveNums > 0) {
                    selectDao.InsertselectRecord(con, select);//保存选择记录
                    result.put("success", true);
                } else {
                    result.put("success", true);
                    result.put("errorMsg", "update error");
                }
            }
            }//--------------唯一判断结束

            ResponseUtil.write(response, result);
            //--------------------------------防止重复操作判断结点-----------------------------------

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
			JSONArray jsonArray=selectDao.getCheckedAuthsByParentId(con, parentId,authIds);
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
			JSONArray jsonArray=selectDao.getListByParentId(con, parentId);
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
        Select select=new Select(authName, authPath, authDescription, iconCls);
		if(StringUtil.isNotEmpty(authId)){
            select.setAuthId(Integer.parseInt(authId));
		}else{
            select.setParentId(Integer.parseInt(parentId));
		}
		Connection con=null;
		boolean isLeaf=false;
		try{
			JSONObject result=new JSONObject();
			con=dbUtil.getCon();
			int saveNums=0;
			if(StringUtil.isNotEmpty(authId)){
				saveNums=selectDao.authUpdate(con, select);
			}else{
				isLeaf=selectDao.isLeaf(con, parentId);
				if(isLeaf){
					con.setAutoCommit(false);
                    selectDao.updateStateByAuthId(con, "closed", parentId);
					saveNums=selectDao.authAdd(con, select);
					con.commit();
				}else{
					saveNums=selectDao.authAdd(con, select);
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
			if(!selectDao.isLeaf(con, authId)){
				result.put("errorMsg", "错误");
			}else{
				int delNums=0;
				sonNum=selectDao.getAuthCountByParentId(con, parentId);
				if(sonNum==1){
					con.setAutoCommit(false);
                    selectDao.updateStateByAuthId(con, "open", parentId);
					delNums=selectDao.authDelete(con, authId);
					con.commit();
				}else{
					delNums=selectDao.authDelete(con, authId);
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
            JSONArray jsonArray=selectDao.getCheckedAuthsByParentId_KQ(con, parentId,authIds);
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
            JSONArray jsonArray=selectDao.getCheckedMenberlistsByParentId(con, parentId,menberIds);
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

        Select select=new Select();
        String s_menberName=request.getParameter("s_menberName");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--姓名
        String s_parentId=request.getParameter("s_parentId");//下拉框里面的内容,此参数是在前端searchmenber()时传递--部门ID
        String s_menberNo=request.getParameter("s_menberNo");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--工号

        String s_division=request.getParameter("s_division");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--姓名
        String s_depart=request.getParameter("s_depart");//下拉框里面的内容,此参数是在前端searchmenber()时传递--部门ID
        String s_departmentname=request.getParameter("s_departmentname");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--工号
        String s_classname=request.getParameter("s_classname");//输入文本框里面的内容,此参数是在前端searchmenber()时传递--工号


        if(StringUtil.isNotEmpty(s_menberName)){
            select.setMenberName(s_menberName);
            System.out.println("start 11111 ");
            System.out.println(s_menberName);
        }

        if(StringUtil.isNotEmpty(s_menberNo)){
            select.setMenberNo(s_menberNo);
            System.out.println("start 33333 ");
            System.out.println(s_menberNo);
        }
        if(StringUtil.isNotEmpty(s_division)){
            select.setDivision(s_division);
            System.out.println("start 666 ");
            System.out.println(s_division);
        }
        if(StringUtil.isNotEmpty(s_depart)){
            select.setDepartment(s_depart);
            System.out.println("start 777 ");
            System.out.println(s_depart);
        }

        if(StringUtil.isNotEmpty(s_departmentname)){
            select.setDepartmentname(s_departmentname);
            System.out.println("start 888 ");
            System.out.println(s_departmentname);
        }
        if(StringUtil.isNotEmpty(s_classname)){
            select.setDepartmentname(s_classname);
            System.out.println("start 999 ");
            System.out.println(s_classname);
        }

        PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();

            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(selectDao.personList(con, pageBean,select));
            int total=selectDao.personCount(con,select);

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
            JSONArray jsonArray=selectDao.getMenberListByParentId(con, parentId);
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
        Select menber=new Select(menberName,menberPath, menberDescription, iconCls,homeaddress,politicalStatus,
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
                saveNums=selectDao.menberUpdate(con, menber);
            }else{
                isLeaf=selectDao.isLeafmenber(con, parentId);
                if(isLeaf){
                    con.setAutoCommit(false);
                    selectDao.updateStateByAuthIdmenber(con, "closed", parentId);
                    saveNums=selectDao.menberAdd(con, menber);
                    con.commit();
                }else{
                    saveNums=selectDao.menberAdd(con, menber);
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

        Select menber=new Select(menberName,menberPath, menberDescription, iconCls,homeaddress,politicalStatus,
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
                saveNums=selectDao.menberUpdate(con, menber);
            }else{
                isLeaf=selectDao.isLeafmenber(con, parentId);
                if(isLeaf){
                    con.setAutoCommit(false);
                    selectDao.updateStateByAuthIdmenber(con, "closed", parentId);
                    saveNums=selectDao.menberAdd(con, menber);
                    con.commit();
                }else{
                    saveNums=selectDao.menberAdd(con, menber);
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
            if(!selectDao.isLeafmenber(con, menberId)){
                result.put("errorMsg", "错误");
            }else{
                int delNums=0;
                sonNum=selectDao.getMenberCountByParentId(con, parentId);

                if(sonNum==1){//如果父亲节点只要当前这个需要删除的孩子，则需要更新其的state字段为closed
                    con.setAutoCommit(false);
                    selectDao.updateStateByMenberId(con, "open", parentId);
                    delNums=selectDao.menberDelete(con, menberId);
                    con.commit();
                }else{
                    result.put("success", true);
                    delNums=selectDao.menberDelete(con, menberId);
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
