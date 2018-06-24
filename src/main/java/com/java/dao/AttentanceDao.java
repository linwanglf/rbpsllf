package com.java.dao;


import com.java.model.Attentance;
import com.java.model.PageBean;
import com.java.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AttentanceDao {


    //项目SQL语句中的?对应的就是setString的字段
    public int att_planAdd(Connection con,Attentance attentance)throws Exception{
       // String sql="insert into t_attentance_plan values(null,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,'no','no','no','no')";
        String sql="insert into t_attentance_plan values(null,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,'no','no','no','no')";
        PreparedStatement pstmt=con.prepareStatement(sql);

        pstmt.setString(1, attentance.getMenberNo());
        pstmt.setString(2, attentance.getMenberName());
        pstmt.setString(3, attentance.getAtt_type());
        pstmt.setString(4,attentance.getAtt_project());
        pstmt.setString(5,attentance.getApply_date());

        pstmt.setString(6, attentance.getApplyer());
        pstmt.setString(7, attentance.getCheckstate());
        pstmt.setString(8, attentance.getChecker());
        pstmt.setString(9, attentance.getCheck_date());//原来单位
        pstmt.setString(10, attentance.getPlan_houre());
        pstmt.setString(11, attentance.getMenbertype());

        pstmt.setString(12, attentance.getDivision());
        pstmt.setString(13, attentance.getDepartment());
        pstmt.setString(14, attentance.getDepartmentname());
        pstmt.setString(15, attentance.getClassname());
        pstmt.setString(16, attentance.getPlan_type());

        pstmt.setInt(17, attentance.getParentId());

        return pstmt.executeUpdate();
    }



    public int att_planUpdate(Connection con,Attentance attentance)throws Exception{
        String sql="update t_attentance_plan set menberNo=?, menberName=?, att_type=?, att_project=?, apply_date=?,\n" +
                "                       applyer=?, checkstate=?,checker=?, check_date=?, plan_houre=?,\n" +
                "                       menbertype=?,  division=?, department=?, departmentname=?, classname=?,plan_type=?,parentId=? where attId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, attentance.getMenberNo());
        pstmt.setString(2, attentance.getMenberName());
        pstmt.setString(3, attentance.getAtt_type());
        pstmt.setString(4,attentance.getAtt_project());
        pstmt.setString(5,attentance.getApply_date());

        pstmt.setString(6, attentance.getApplyer());
        pstmt.setString(7, attentance.getCheckstate());
        pstmt.setString(8, attentance.getChecker());
        pstmt.setString(9, attentance.getCheck_date());//原来单位
        pstmt.setString(10, attentance.getPlan_houre());
        pstmt.setString(11, attentance.getMenbertype());

        pstmt.setString(12, attentance.getDivision());
        pstmt.setString(13, attentance.getDepartment());
        pstmt.setString(14, attentance.getDepartmentname());
        pstmt.setString(15, attentance.getClassname());
        pstmt.setString(16, attentance.getPlan_type());

        pstmt.setInt(17, attentance.getParentId());
        pstmt.setInt(18, attentance.getAttId());
        return pstmt.executeUpdate();
    }


    public boolean isLeafatt_plan(Connection con,String parentId)throws Exception{
        String sql="select * from t_attentance_plan where parentId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId);
        ResultSet rs=pstmt.executeQuery();
        return !rs.next();
    }



    public int att_planDelete(Connection con,String attId)throws Exception{
//        String sql="delete from t_attentance_plan where attId=?";
//        PreparedStatement pstmt=con.prepareStatement(sql);
//        pstmt.setString(1, attId);
//        return pstmt.executeUpdate();
        String sql="delete from t_attentance_plan where attId in ("+attId+")";
        PreparedStatement pstmt=con.prepareStatement(sql);
        return pstmt.executeUpdate();

    }

    public int att_planCheck(Connection con,String attId)throws Exception{
        String sql="update t_attentance_plan set check_state_department=? where attId in ("+attId+")";
        String state="ok";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, state);
        return pstmt.executeUpdate();

    }
    public int att_planCancel(Connection con,String attId)throws Exception{
        String sql="update t_attentance_plan set check_state_department=? where attId in ("+attId+")";
        String state="ng";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, state);
        return pstmt.executeUpdate();

    }

    public ResultSet att_plan_applyList(Connection con, PageBean pageBean, Attentance attentance)throws Exception{

        String stemp="000000";
        StringBuffer sb=new StringBuffer("select * from t_attentance_plan");
        //StringBuffer sb=new StringBuffer("select * from t_menber where  menberNo NOT IN('"+stemp+"')");
        //StringBuffer sb=new StringBuffer("select * from t_menber where menberNo NOT IN('000000')");
        if(StringUtil.isNotEmpty(attentance.getMenberName())){
            sb.append(" and menberName like '%"+attentance.getMenberName()+"%'");
        }
        if(StringUtil.isNotEmpty(attentance.getClassname())){
            sb.append(" and classname like '%"+attentance.getClassname()+"%'");
        }else{
            if(StringUtil.isNotEmpty(attentance.getDepartmentname())){
            sb.append(" and departmentname like '%"+attentance.getDepartmentname()+"%'");

            }else
            {
                if (StringUtil.isNotEmpty(attentance.getDepartment())) {
                    sb.append(" and department like '%" + attentance.getDepartment() + "%'");

                } else
                {
                    if (StringUtil.isNotEmpty(attentance.getDivision())) {
                        sb.append(" and division like '%" + attentance.getDivision() + "%'");
                    }
                }

            }
        }

        if(attentance.getParentId()!=-1){
            sb.append(" and parentId="+attentance.getParentId());
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        //PreparedStatement pstmt=con.prepareStatement(sb.toString());
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    public int att_plan_applyCount(Connection con,Attentance attentance)throws Exception{

        String stemp="000000";
        StringBuffer sb=new StringBuffer("select count(*) as total from t_attentance_plan ");
        if(StringUtil.isNotEmpty(attentance.getMenberName())){
            sb.append(" and menberName like '%"+attentance.getMenberName()+"%'");
        }
        if(attentance.getParentId()!=-1){
            sb.append(" and parentId="+attentance.getParentId());
        }

        // PreparedStatement pstmt=con.prepareStatement(sb.toString());

        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));




        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }

//-----------------------------------------------------

    public int att_realAdd(Connection con,Attentance attentance)throws Exception{
        // String sql="insert into t_attentance_plan values(null,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,'no','no','no','no')";
        String sql="insert into t_attentance_plan values(null,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,'no','no','no','no')";
        PreparedStatement pstmt=con.prepareStatement(sql);

        pstmt.setString(1, attentance.getMenberNo());
        pstmt.setString(2, attentance.getMenberName());
        pstmt.setString(3, attentance.getAtt_type());
        pstmt.setString(4,attentance.getAtt_project());
        pstmt.setString(5,attentance.getApply_date());

        pstmt.setString(6, attentance.getApplyer());
        pstmt.setString(7, attentance.getCheckstate());
        pstmt.setString(8, attentance.getChecker());
        pstmt.setString(9, attentance.getCheck_date());//原来单位
        pstmt.setString(10, attentance.getPlan_houre());
        pstmt.setString(11, attentance.getMenbertype());

        pstmt.setString(12, attentance.getDivision());
        pstmt.setString(13, attentance.getDepartment());
        pstmt.setString(14, attentance.getDepartmentname());
        pstmt.setString(15, attentance.getClassname());
        pstmt.setString(16, attentance.getPlan_type());

        pstmt.setInt(17, attentance.getParentId());

        return pstmt.executeUpdate();
    }



    public int att_realUpdate(Connection con,Attentance attentance)throws Exception{
        String sql="update t_attentance_plan set menberNo=?, menberName=?, att_type=?, att_project=?, apply_date=?,\n" +
                "                       applyer=?, checkstate=?,checker=?, check_date=?, plan_houre=?,\n" +
                "                       menbertype=?,  division=?, department=?, departmentname=?, classname=?,plan_type=?,parentId=? where attId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, attentance.getMenberNo());
        pstmt.setString(2, attentance.getMenberName());
        pstmt.setString(3, attentance.getAtt_type());
        pstmt.setString(4,attentance.getAtt_project());
        pstmt.setString(5,attentance.getApply_date());

        pstmt.setString(6, attentance.getApplyer());
        pstmt.setString(7, attentance.getCheckstate());
        pstmt.setString(8, attentance.getChecker());
        pstmt.setString(9, attentance.getCheck_date());//原来单位
        pstmt.setString(10, attentance.getPlan_houre());
        pstmt.setString(11, attentance.getMenbertype());

        pstmt.setString(12, attentance.getDivision());
        pstmt.setString(13, attentance.getDepartment());
        pstmt.setString(14, attentance.getDepartmentname());
        pstmt.setString(15, attentance.getClassname());
        pstmt.setString(16, attentance.getPlan_type());

        pstmt.setInt(17, attentance.getParentId());
        pstmt.setInt(18, attentance.getAttId());
        return pstmt.executeUpdate();
    }

    public boolean isLeafatt_real(Connection con,String parentId)throws Exception{
        String sql="select * from t_attentance_plan where parentId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId);
        ResultSet rs=pstmt.executeQuery();
        return !rs.next();
    }


    public int att_realDelete(Connection con,String attId)throws Exception{
//        String sql="delete from t_attentance_plan where attId=?";
//        PreparedStatement pstmt=con.prepareStatement(sql);
//        pstmt.setString(1, attId);
//        return pstmt.executeUpdate();
        String sql="delete from t_attentance_real where attId in ("+attId+")";
        PreparedStatement pstmt=con.prepareStatement(sql);
        return pstmt.executeUpdate();

    }

    public int att_realCheck(Connection con,String attId)throws Exception{
        String sql="update t_attentance_real set check_state_department=? where attId in ("+attId+")";
        String state="ok";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, state);
        return pstmt.executeUpdate();

    }
    public int att_realCancel(Connection con,String attId)throws Exception{
        String sql="update t_attentance_real set check_state_department=? where attId in ("+attId+")";
        String state="ng";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, state);
        return pstmt.executeUpdate();

    }
    public int updateStateByMenberId(Connection con,String state,String authId)throws Exception{
        String sql="update t_menber set state=? where menberId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, state);
        pstmt.setString(2, authId);
        return pstmt.executeUpdate();
    }
	public int getAuthCountByParentId(Connection con,String parentId)throws Exception{
		String sql="select count(*) as total from t_auth where parentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}

    public int getMenberCountByParentId(Connection con,String parentId)throws Exception{
        String sql="select count(*) as total from t_menber where parentId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }

    public ResultSet att_real_applyList(Connection con, PageBean pageBean, Attentance attentance)throws Exception{

        String stemp="000000";
        StringBuffer sb=new StringBuffer("select * from t_attentance_plan");
        //StringBuffer sb=new StringBuffer("select * from t_menber where  menberNo NOT IN('"+stemp+"')");
        //StringBuffer sb=new StringBuffer("select * from t_menber where menberNo NOT IN('000000')");
        if(StringUtil.isNotEmpty(attentance.getMenberName())){
            sb.append(" and menberName like '%"+attentance.getMenberName()+"%'");
        }
        if(attentance.getParentId()!=-1){
            sb.append(" and parentId="+attentance.getParentId());
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        //PreparedStatement pstmt=con.prepareStatement(sb.toString());
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    public int att_real_applyCount(Connection con,Attentance attentance)throws Exception{

        String stemp="000000";
        StringBuffer sb=new StringBuffer("select count(*) as total from t_attentance_plan ");
        if(StringUtil.isNotEmpty(attentance.getMenberName())){
            sb.append(" and menberName like '%"+attentance.getMenberName()+"%'");
        }
        if(attentance.getParentId()!=-1){
            sb.append(" and parentId="+attentance.getParentId());
        }

        // PreparedStatement pstmt=con.prepareStatement(sb.toString());

        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));




        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }

    public ResultSet attentance_planList(Connection con, PageBean pageBean)throws Exception{
        StringBuffer sb=new StringBuffer("select * from t_attentance_plan");
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//        if(pageBean!=null){
//            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
//        }
        return pstmt.executeQuery();
    }
    //根据科室名查询当前科有记录的所有员工工号
    public ResultSet attentance_planList_bydepartment(Connection con, PageBean pageBean,String department)throws Exception{
//        String department_sel="pp";
//        StringBuffer sb=new StringBuffer("select distinct(menberNo) from t_attentance_plan  where department =?");
//        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//        pstmt.setString(1, department_sel);
//        return pstmt.executeQuery();

        //String department_sel="pp";
        String sql="select distinct(menberNo) from t_attentance_plan  where department =?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, department);
        return pstmt.executeQuery();
    }

    //根据工号查询相关的记录
    public ResultSet attentance_planList_bymenberNo(Connection con, PageBean pageBean,String menberNo)throws Exception{
        StringBuffer sb=new StringBuffer("select * from t_attentance_plan where menberNo="+menberNo);
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//        if(pageBean!=null){
//            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
//        }
//        log.info("SQL: {}", sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }
}
